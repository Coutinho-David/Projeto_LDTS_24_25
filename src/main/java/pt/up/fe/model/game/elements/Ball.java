package pt.up.fe.model.game.elements;

import pt.up.fe.model.Position;

import java.util.Random;

public class Ball implements Element {
    private final Position position;
    private int vx; //velocities
    private int vy;
    private boolean isInvisible = false;
    private boolean isWeird = false;

    public Ball(int x, int y) {
        this.position = new Position(x, y);
        Random random = new Random();
        this.vx = random.nextBoolean() ? 1 : -1; // Randomize horizontal velocity
        this.vy = random.nextBoolean() ? 1 : -1; // Randomize vertical velocity
    }


    public void move() {
        setX(getX() + vx);
        setY(getY() + vy);
    }

    public void reverseX() {
        vx = -vx;
    }

    public void reverseY() {
        vy = -vy;
    }

    public int getVelocityX() {return vx;}

    public int getVelocityY() {return vy;}

    public void setVelocities(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void normalizeSpeed(int maxSpeed) {
        int currentSpeed = (int) Math.sqrt(vx * vx + vy * vy);

        // If the current speed exceeds the maxSpeed, scale it down
        if (currentSpeed > maxSpeed) {
            double scale = (double) maxSpeed / currentSpeed;
            vx = (int) Math.round(vx * scale);
            vy = (int) Math.round(vy * scale);
        }

    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public void setX(int x) {
        position.setX(x);
    }

    @Override
    public void setY(int y) {
        position.setY(y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public boolean isInvisible() {
        return isInvisible;
    }

    public void setInvisible(boolean invisible) {
        isInvisible = invisible;
    }

    public boolean isWeird() {
        return isWeird;
    }

    public void setWeird(boolean weird) {
        isWeird = weird;
    }
}
