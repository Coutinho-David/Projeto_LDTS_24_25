package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUp;

import java.util.Timer;
import java.util.TimerTask;

public class ReverseControlsPowerUP implements PowerUp {
    private final Position position;

    public ReverseControlsPowerUP(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("REVERSE CONTROLS");

        //Set Effect on Player
        if (touch == 1) {
            arena.getPlayerOne().setReversed(true);
        } else {
            arena.getPlayerTwo().setReversed(true);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (touch == 1) {
                    arena.getPlayerOne().setReversed(false);
                } else {
                    arena.getPlayerTwo().setReversed(false);
                }
                System.out.println("Reverse Controls PowerUP over");
            }
        }, 5000); //5 secs
    }

    @Override
    public void draw(LanternaGUI GUI) {
        GUI.drawPowerUp(position.getX(), position.getY(), "#FF33FF");
    }
}