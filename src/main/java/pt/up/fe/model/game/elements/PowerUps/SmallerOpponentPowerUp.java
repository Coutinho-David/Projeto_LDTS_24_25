package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUp;
import java.util.Timer;
import java.util.TimerTask;

public class SmallerOpponentPowerUp implements PowerUp {
    private final Position position;

    public SmallerOpponentPowerUp(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("SMALLER OPPONENT");

        //Set Effect on Opponent
        if (touch == 0) {
            arena.getPlayerTwo().setSize(5);
        } else {
            arena.getPlayerOne().setSize(5);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (touch == 0) {
                    arena.getPlayerTwo().resetSize();
                } else {
                    arena.getPlayerOne().resetSize();
                }
                    System.out.println("Smaller Opponent PowerUP over");
            }
        }, 10000);//10secs
    }

    @Override
    public void draw(LanternaGUI GUI) {
        GUI.drawPowerUp(position.getX(), position.getY(), "#9F33FF");
    }
}