package pt.up.fe.model.game.elements;

import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.PowerUps.*;

import java.util.Random;

public class PowerUpFactory {

    private Arena arena;

    public PowerUpFactory(Arena arena) {
        this.arena = arena;
    }

    public PowerUp createRandomPowerUP() {
        Random random = new Random();
        int numOfAbilities = 7;

        int borderWidthOffLimits = (int) (arena.getWidth() * 0.2); //only spawn in the 60% middle
        int borderHeightOffLimits = (int) (arena.getHeight() * 0.1); //only spawn in the 80% middle

        int x = borderWidthOffLimits + random.nextInt(arena.getWidth() - 2 * borderWidthOffLimits);
        int y = borderHeightOffLimits + random.nextInt(arena.getHeight() - 2 * borderHeightOffLimits);

        Position position = new Position(x, y);

        int powerUpType = random.nextInt(numOfAbilities);

        return switch (powerUpType) {
            case 0 -> new BiggerPlayerPowerUP(position);
            case 1 -> new DoublePointsPowerUP(position);
            case 2 -> new FasterPlayerPowerUP(position);
            case 3 -> new InvisibleBallPowerUp(position);
            case 4 -> new ReverseControlsPowerUP(position);
            case 5 -> new SmallerOpponentPowerUp(position);
            case 6 -> new WeirdBouncePowerUp(position);
            default -> throw new IllegalArgumentException("Invalid power up type");
        };
    }
}