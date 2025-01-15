package pt.up.fe.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.model.game.elements.Player;

public class PlayerTest {
    Player player1 = new Player(10, 5);
    Player player2 = new Player(10, 4);

    @Test
    public void getPosition() {
        Assertions.assertEquals(10, player1.getPosition().getX());
        Assertions.assertEquals(5, player1.getPosition().getY());
    }


    @Test
    public void moveUp() {

        player1.moveUp();
        Assertions.assertEquals(3, player1.getY());

        player1.setY(2);
        player2.setY(1);
        player1.moveUp();
        player2.moveUp();
        Assertions.assertEquals(1, player1.getY());
        Assertions.assertEquals(1, player2.getY());

        player1.setY(5);
        player1.setFaster(true);
        player1.moveUp();
        Assertions.assertEquals(2, player1.getY());

    }

    @Test
    public void moveDown() {
        player2.moveDown(15);
        Assertions.assertEquals(6, player2.getY());
        player2.moveDown(15);
        Assertions.assertEquals(6, player2.getY());

        player1.setFaster(true);
        player1.moveDown(16);
        Assertions.assertEquals(8, player1.getY());
        player1.moveDown(16);
        Assertions.assertEquals(8, player1.getY());
    }

    @Test
    public void getSize() {
        Assertions.assertEquals(7, player1.getSize());
        Assertions.assertEquals(7, player2.getSize());
    }

    @Test
    public void setSize() {
        player1.setSize(10);
        Assertions.assertEquals(10, player1.getSize());
        player2.setSize(5);
        Assertions.assertEquals(5, player2.getSize());
    }

    @Test
    public void resetSize() {
        player1.setSize(10);
        Assertions.assertEquals(10, player1.getSize());
        player1.resetSize();
        Assertions.assertEquals(7, player1.getSize());
    }

    @Test
    public void isFaster() {
        Assertions.assertFalse(player1.isFaster());
    }

    @Test
    public void setFaster() {
        player1.setFaster(true);
        Assertions.assertTrue(player1.isFaster());
    }

    @Test
    public void isReversed() {
        Assertions.assertFalse(player1.isReversed());
    }

    @Test
    public void setReversed() {
        player1.setReversed(true);
        Assertions.assertTrue(player1.isReversed());
        Assertions.assertFalse(player2.isReversed());
    }
}
