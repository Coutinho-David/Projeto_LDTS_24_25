package pt.up.fe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUp;
import pt.up.fe.model.game.elements.PowerUpFactory;

import static org.junit.jupiter.api.Assertions.*;


public class PowerUpFactoryTest {

    private PowerUpFactory factory;

    @BeforeEach
    public void setUp() {
        Arena arena = Mockito.mock(Arena.class);

        Mockito.when(arena.getWidth()).thenReturn(5);
        Mockito.when(arena.getHeight()).thenReturn(10);

        factory = new PowerUpFactory(arena);
    }

    @Test
    public void createRandomPowerUpTest() {

        PowerUp powerUp = factory.createRandomPowerUP();
        Position position = powerUp.getPosition();

        assertNotNull(powerUp);
        assertInstanceOf(PowerUp.class, powerUp);

        //int x = random ate 3
        //int y = random ate 9

        assertTrue(position.getX() >= 1 && position.getX() <= 3);
        assertTrue(position.getY() >= 1 && position.getY() <= 9);

    }

}
