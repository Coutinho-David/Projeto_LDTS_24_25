package pt.up.fe.model;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import pt.up.fe.model.game.Stats;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.game.elements.PowerUp;
import pt.up.fe.model.game.elements.PowerUps.BiggerPlayerPowerUP;
import pt.up.fe.model.game.elements.PowerUps.FasterPlayerPowerUP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaTest {


    private final Player player1 = new Player(10, 10);
    private final Player player2 = new Player(20, 20);
    private final List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
    private final Ball ball = new Ball(30, 20);
    private final Stats stats = new Stats();
    private final int seriesLength = 3;
    private final List<PowerUp> activePowerUps = new ArrayList<>();
    private final Arena arena = new Arena(60, 40, players, ball, stats, seriesLength, activePowerUps);


    @Test
    public void getWidth() {
        Assertions.assertEquals(60, arena.getWidth());
    }

    @Test
    public void getHeight() {
        Assertions.assertEquals(40, arena.getHeight());
    }

    @Test
    public void getBall() {
        Assertions.assertEquals(ball, arena.getBall());
    }

    @Test
    public void getPlayers() {
        Assertions.assertEquals(players, arena.getPlayers());
    }

    @Test
    public void getPlayerOne() {
        Assertions.assertEquals(players.getFirst(), arena.getPlayerOne());
    }

    @Test
    public void getPlayerTwo() {
        Assertions.assertEquals(players.getLast(), arena.getPlayerTwo());
    }

    @Test
    public void addPlayer() {
        Player newPlayer = new Player(30, 30);
        arena.addPlayer(newPlayer);
        Assertions.assertTrue(arena.getPlayers().contains(newPlayer));
    }

    @Test
    public void getStats() {
        Assertions.assertEquals(stats, arena.getStats());
    }

    @Test
    public void getSeriesLength() {
        Assertions.assertEquals(seriesLength, arena.getSeriesLength());
    }

    @Test
    public void setSeriesLength() {
        arena.setSeriesLength(5);
        Assertions.assertEquals(5, arena.getSeriesLength());
    }

    @Test
    public void subGameTime() {
        Assertions.assertEquals(120, arena.getGameTime());
        arena.subGameTime();
        Assertions.assertEquals(119, arena.getGameTime());
    }

    @Test
    public void resetGameTime() {
        arena.subGameTime();
        arena.subGameTime();
        Assertions.assertNotEquals(120, arena.getGameTime());
        arena.resetGameTime();
        Assertions.assertEquals(120, arena.getGameTime());
    }

    @Test
    public void subCountDown() {
        Assertions.assertEquals(3, arena.getCountDown());
        arena.subCountDown();
        Assertions.assertEquals(2, arena.getCountDown());
    }

    @Test
    public void resetCountDown() {
        arena.subCountDown();
        Assertions.assertNotEquals(3, arena.getCountDown());
        arena.resetCountDown();
        Assertions.assertEquals(3, arena.getCountDown());
    }

    @Test
    public void setActivePowerUps() {
        PowerUp fasterPlayer = new FasterPlayerPowerUP(new Position(3, 3));
        PowerUp biggerPlayer = new BiggerPlayerPowerUP(new Position(10, 7));
        List<PowerUp> activePowerUps = new ArrayList<>(Arrays.asList(fasterPlayer, biggerPlayer));
        arena.setActivePowerUps(activePowerUps);
        Assertions.assertEquals(activePowerUps, arena.getActivePowerUps());
    }

    @Test
    public void resetActivePowerUps() {
        List<PowerUp> activePowerUps = new ArrayList<>();
        arena.resetActivePowerUps();
        Assertions.assertEquals(activePowerUps, arena.getActivePowerUps());
    }

    @Test
    public void setLastPowerUpTime() {
        arena.setLastPowerUpTime();
        Assertions.assertEquals(System.currentTimeMillis(), arena.getLastPowerUpTime());
    }

    @Test
    public void setPowerUpCaught() {
        arena.setPowerUpCaught("aaa");
        Assertions.assertEquals("aaa", arena.getPowerUpCaught());
    }


    @Test
    public void resetPowerUpCaught() {
        arena.resetPowerUpCaught();
        Assertions.assertEquals("", arena.getPowerUpCaught());
    }

    @Test
    public void setPowerUpCaughtTime() {
        arena.setPowerUpCaughtTime(5);
        Assertions.assertEquals(5, arena.getPowerUpCaughtTime());
    }
}

