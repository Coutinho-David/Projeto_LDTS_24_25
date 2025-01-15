package pt.up.fe.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.model.game.elements.Ball;

public class BallTest {

    Ball ball = new Ball(20, 20);

    @BeforeEach
    public void setUp() {
        ball.setVelocities(1, 1);
    }

    @Test
    public void getPosition() {
        Assertions.assertEquals(20, ball.getPosition().getX());
        Assertions.assertEquals(20, ball.getPosition().getY());
    }

    @Test
    public void move() {
        ball.move();
        Assertions.assertEquals(21, ball.getX());
        Assertions.assertEquals(21, ball.getY());
    }

    @Test
    public void getVelocityX() {
        Assertions.assertEquals(1, ball.getVelocityX());
    }

    @Test
    public void getVelocityY() {
        Assertions.assertEquals(1, ball.getVelocityY());
    }

    @Test
    public void reverseX() {
        ball.reverseX();
        Assertions.assertEquals(-1, ball.getVelocityX());
    }

    @Test
    public void reverseY() {
        ball.reverseY();
        Assertions.assertEquals(-1, ball.getVelocityY());
    }

    @Test
    public void setVelocities() {
        ball.setVelocities(2, 2);
        Assertions.assertEquals(2, ball.getVelocityX());
        Assertions.assertEquals(2, ball.getVelocityY());
    }

    @Test
    public void normalizeSpeed() {
        ball.normalizeSpeed(1);
        Assertions.assertEquals(1, ball.getVelocityX());
        Assertions.assertEquals(1, ball.getVelocityY());
    }

    @Test
    public void isInvisible()
    {
        Assertions.assertFalse(ball.isInvisible());
    }

    @Test
    public void setInvisible()
    {
        ball.setInvisible(true);
        Assertions.assertTrue(ball.isInvisible());
    }

    @Test
    public void isWeird() {
        Assertions.assertFalse(ball.isWeird());
    }

    @Test
    public void setWeird() {
        ball.setWeird(true);
        Assertions.assertTrue(ball.isWeird());
    }
}
