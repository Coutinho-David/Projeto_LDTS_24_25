package pt.up.fe.model.game.elements.PowerUps;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.PowerUp;

import java.util.Timer;
import java.util.TimerTask;

public class WeirdBouncePowerUp implements PowerUp {
    private Position position;

    public WeirdBouncePowerUp(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void activate(Arena arena, int touch) {
        arena.setPowerUpCaught("WEIRD BOUNCE");
        Ball ball = arena.getBall();

        ball.setWeird(true);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ball.setWeird(false);
                System.out.println("Weird Bounce PowerUP over");
            }
        }, 10000);//10 secs
    }

    @Override
    public void draw(LanternaGUI GUI) {
        GUI.drawPowerUp(position.getX(), position.getY(), "#33FF9F");
    }
}