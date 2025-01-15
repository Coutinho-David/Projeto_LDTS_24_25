package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.PowerUp;

import java.util.Timer;
import java.util.TimerTask;

public class InvisibleBallPowerUp implements PowerUp {
    private final Position position;

    public InvisibleBallPowerUp(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("INVISIBLE BALL");

        Ball ball = arena.getBall();

        ball.setInvisible(true);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ball.setInvisible(false);
                System.out.println("Invisible Ball PowerUP over");
            }
        }, 3000);//3secs
    }

    @Override
    public void draw(LanternaGUI GUI) {
        GUI.drawPowerUp(position.getX(), position.getY(), "#33FFFF");
    }
}