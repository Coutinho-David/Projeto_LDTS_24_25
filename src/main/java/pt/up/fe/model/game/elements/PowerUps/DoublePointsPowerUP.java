package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUp;

import java.util.Timer;
import java.util.TimerTask;


public class DoublePointsPowerUP implements PowerUp {
    private final Position position;
    private static boolean isActive = false;

    public DoublePointsPowerUP(Position position) {
        this.position = position;
    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("DOUBLE POINTS");

        isActive = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isActive = false;
                System.out.println("Double Points PowerUP over");
            }
        }, 10000); //10secs
    }

    @Override
    public void draw(LanternaGUI GUI) {
        GUI.drawPowerUp(position.getX(), position.getY(), "#33FF57");
    }

    public static boolean isActive() {
        return isActive;
    }
}