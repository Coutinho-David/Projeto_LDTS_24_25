package pt.up.fe.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.model.game.elements.PowerUp;
import pt.up.fe.model.game.elements.PowerUps.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerUpTest {

    private final Position position = new Position(5, 5);
    private final PowerUp biggerPlayerPowerUP = new BiggerPlayerPowerUP(position);
    private final PowerUp doublePointsPowerUP = new DoublePointsPowerUP(position);
    private final PowerUp invisibleBallPowerUP = new InvisibleBallPowerUp(position);
    private final PowerUp reverseControlsPowerUP = new ReverseControlsPowerUP(position);
    private final PowerUp smallerOpponentPowerUp = new SmallerOpponentPowerUp(position);
    private final PowerUp weirdBouncePowerUp = new WeirdBouncePowerUp(position);
    private final PowerUp fasterPlayerPowerUP = new FasterPlayerPowerUP(position);

    private final List<PowerUp> powerUps = new ArrayList<>(Arrays.asList(biggerPlayerPowerUP, doublePointsPowerUP, invisibleBallPowerUP, reverseControlsPowerUP, smallerOpponentPowerUp, weirdBouncePowerUp, fasterPlayerPowerUP));

    @Test
    public void getPositionTest() {
        for (PowerUp powerUp : powerUps) {
            Assertions.assertEquals(position, powerUp.getPosition());
        }
    }

    /*
    @Test
    public void activateTest() {
        for (PowerUp powerUp : powerUps) {
            powerUp.activate();
        }
    }*/

}
