package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUp;

import java.util.Timer;
import java.util.TimerTask;

public class FasterPlayerPowerUP implements PowerUp {
    private final Position position;

    public FasterPlayerPowerUP(Position position) {
        this.position = position;
    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("FASTER PLAYER");

        //Set Effect on Player
        if (touch == 1) {
            arena.getPlayerTwo().setFaster(true);
        } else {
            arena.getPlayerOne().setFaster(true);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (touch == 1) {
                    arena.getPlayerTwo().setFaster(false);
                } else {
                    arena.getPlayerOne().setFaster(false);
                }
                System.out.println("Faster Player PowerUP over");
            }
        }, 5000);//3secs
    }

    @Override
    public void draw(LanternaGUI GUI) {
        GUI.drawPowerUp(position.getX(), position.getY(), "#FFFF33");
    }
}