package pt.up.fe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PositionTest {

    Position testPosition = new Position(20, 20);

    @Test
    public void getX() {
        assertEquals(20, testPosition.getX());
    }

    @Test
    public void getY() {
        assertEquals(20, testPosition.getY());
    }

    @Test
    public void setX() {
        testPosition.setX(40);
        assertEquals(40, testPosition.getX());
    }

    @Test
    public void setY() {
        testPosition.setY(50);
        assertEquals(50, testPosition.getY());
    }

}
