package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUp;
import java.util.Timer;
import java.util.TimerTask;

public class BiggerPlayerPowerUP implements PowerUp {
    private final Position position;

    public BiggerPlayerPowerUP(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("BIGGER PLAYER");

        //Set Effect on Player
        if (touch == 1) {
            arena.getPlayerTwo().setSize(9);
        } else {
            arena.getPlayerOne().setSize(9);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (touch == 1) {
                    arena.getPlayerTwo().resetSize();
                } else {
                    arena.getPlayerOne().resetSize();
                }
                System.out.println("Bigger Player PowerUP over");
            }
        }, 10000);
    }

    @Override
    public void draw(LanternaGUI GUI){
        GUI.drawPowerUp(position.getX(), position.getY(), "#FF5733");
    }
}