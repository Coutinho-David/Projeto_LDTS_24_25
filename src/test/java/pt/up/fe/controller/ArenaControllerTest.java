package pt.up.fe.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.controller.game.ArenaController;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.Position;
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

public class ArenaControllerTest {

    private final Player player1 = new Player(10, 10);
    private final Player player2 = new Player(20, 20);
    private final List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
    private final Ball ball = new Ball(30, 20);
    private final Stats stats = new Stats();
    private final int seriesLength = 3;
    private final List<PowerUp> activePowerUps = new ArrayList<>();

    Arena arena = new Arena(100, 100, players, ball, stats, seriesLength, activePowerUps);
    ArenaController arenaController = new ArenaController(arena);

    @Test
    public void ballAngleTest() {
        arena.getBall().setY(3);
        arena.getPlayerOne().setY(2);

        arenaController.BallAngle();

        //angle = 1
        //angle == 0, 1, 2

        Assertions.assertTrue(arena.getBall().getVelocityY() >= -3 & arena.getBall().getVelocityY() <= 3, "is " + arena.getBall().getY());
    }

    @Test
    public void checkCollisions() {
        player1.setX(2);
        player1.setY(2);
        ball.setX(3);
        ball.setY(3);
        ball.setVelocities(1, 1);

        PowerUp fasterPlayer = new FasterPlayerPowerUP(new Position(3, 3));
        PowerUp biggerPlayer = new BiggerPlayerPowerUP(new Position(10, 10));
        List<PowerUp> powerUps = new ArrayList<>(Arrays.asList(fasterPlayer, biggerPlayer));

        arena.setActivePowerUps(powerUps);

        arenaController.checkCollisions();

        Assertions.assertEquals(-1, arena.getBall().getVelocityX());
        Assertions.assertEquals(3, arena.getBall().getX());
        Assertions.assertTrue(arena.getBall().getVelocityY() == -1 || arena.getBall().getVelocityY() == 0 || arena.getBall().getVelocityY() == 1);
        Assertions.assertTrue(arena.getActivePowerUps().size() < 3);

        player1.setX(20);
        player1.setY(20);

        player2.setX(5);
        player2.setY(5);
        ball.setX(4);
        ball.setY(4);
        ball.setVelocities(1, 1);

        arenaController.checkCollisions();

       Assertions.assertEquals(1, arena.getBall().getVelocityX());
       Assertions.assertEquals(4, arena.getBall().getX());
       Assertions.assertTrue(arena.getBall().getVelocityY() == -1 || arena.getBall().getVelocityY() == 0 || arena.getBall().getVelocityY() == 1);

       ball.setX(0);

       arenaController.checkCollisions();
       Assertions.assertEquals(1, arena.getStats().getScore2());

       ball.setX(arena.getWidth() - 1);
       arenaController.checkCollisions();
       Assertions.assertEquals(1, arena.getStats().getScore1());

        ball.setY(1);
        ball.setVelocities(-1, -1);

        arenaController.checkCollisions();
        Assertions.assertEquals(1, ball.getVelocityY());

        ball.setX(0);
        ball.setVelocities(-1, ball.getVelocityY());

        arenaController.checkCollisions();
        Assertions.assertEquals(2, arena.getStats().getScore2());

        ball.setX(player1.getX() + 1);
        ball.setY(player1.getY() + 1);
        ball.setVelocities(1, 1);

        arenaController.checkCollisions();

        Assertions.assertEquals(-1, ball.getVelocityX());
    }

    @Test
    public void resetBall() {
        arenaController.resetBall();

        Assertions.assertEquals(49, arena.getBall().getX());
        Assertions.assertEquals(50, arena.getBall().getY());
        Assertions.assertTrue(arena.getBall().getVelocityX() >= -1 & arena.getBall().getVelocityX() <= 1);
        Assertions.assertTrue(arena.getBall().getVelocityY() >= -1 & arena.getBall().getVelocityY() <= 1);

    }

    @Test
    public void resetPlayerOne() {
        arenaController.resetPlayerOne();
        Assertions.assertEquals(4, arena.getPlayerOne().getX());
        Assertions.assertEquals(47, arena.getPlayerOne().getY());
    }

    @Test
    public void resetPlayerTwo() {
        arenaController.resetPlayerTwo();
        Assertions.assertEquals(95, arena.getPlayerTwo().getX());
        Assertions.assertEquals(47, arena.getPlayerTwo().getY());
    }

    @Test
    public void spawnPowerUps() {

        arena.resetActivePowerUps();
        arenaController.spawnPowerUps();
        Assertions.assertFalse(arena.getActivePowerUps().isEmpty());
    }


    @Test
    public void testGoalPlayer1Scores() {
        ball.setX(arena.getWidth() - 1);
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        Assertions.assertEquals(3, arena.getStats().getScore1());
        arenaController.goal();
        Assertions.assertEquals(4, arena.getStats().getScore1());
        arenaController.goal();

        Assertions.assertEquals(0, arena.getStats().getScore1());
        Assertions.assertEquals(0, arena.getStats().getScore2());
    }

    @Test
    public void testGoalPlayer2Scores() {
        ball.setX(0);
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        Assertions.assertEquals(3, arena.getStats().getScore2());
        arenaController.goal();
        Assertions.assertEquals(4, arena.getStats().getScore2());
        ball.setX(0);
        arenaController.goal();

        Assertions.assertEquals(0, arena.getStats().getScore2());
        Assertions.assertEquals(0, arena.getStats().getScore1());
    }

    @Test
    public void testMatchWinPlayer1Wins() {
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();

        arenaController.matchWin();

        Assertions.assertEquals(1, arena.getStats().getWins1Count());

        Assertions.assertEquals(0, arena.getStats().getScore1());
        Assertions.assertEquals(0, arena.getStats().getScore2());

        Assertions.assertEquals(50, arena.getBall().getY());
        Assertions.assertEquals(4, arena.getPlayerOne().getX());
    }

    @Test
    public void testMatchWinPlayer2Wins() {
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore1();
        arena.getStats().setScore1();

        arenaController.matchWin();

        Assertions.assertEquals(1, arena.getStats().getWins2Count());

        Assertions.assertEquals(0, arena.getStats().getScore1());
        Assertions.assertEquals(0, arena.getStats().getScore2());

        Assertions.assertEquals(50, arena.getBall().getY());
        Assertions.assertEquals(95, arena.getPlayerTwo().getX());
    }

    @Test
    public void testSeriesWinPlayer1Wins() {
        arena.setSeriesLength(3);
        arena.getStats().setWins1Count();
        arena.getStats().setWins1Count();

        arenaController.seriesWin();

        Assertions.assertEquals("PLAYER_1", arenaController.getWinner());

        Assertions.assertEquals(0, arena.getStats().getWins1Count());
        Assertions.assertEquals(0, arena.getStats().getWins2Count());
    }

    @Test
    public void testSeriesWinPlayer2Wins() {
        arena.setSeriesLength(3);
        arena.getStats().setWins2Count();
        arena.getStats().setWins2Count();

        arenaController.seriesWin();


        Assertions.assertEquals("PLAYER_2", arenaController.getWinner());

        Assertions.assertEquals(0, arena.getStats().getWins1Count());
        Assertions.assertEquals(0, arena.getStats().getWins2Count());
    }

    @Test
    public void testTieBreakerPlayer1Wins() {
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore1();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arenaController.tieBreaker();


        Assertions.assertEquals(1, arena.getStats().getWins1Count());

        Assertions.assertEquals(0, arena.getStats().getScore1());
        Assertions.assertEquals(0, arena.getStats().getScore2());

        Assertions.assertFalse(arenaController.isWon());
    }

    @Test
    public void testTieBreakerPlayer2Wins() {
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore2();
        arena.getStats().setScore1();

        arenaController.tieBreaker();

        Assertions.assertEquals(1, arena.getStats().getWins2Count());

        Assertions.assertEquals(0, arena.getStats().getScore1());
        Assertions.assertEquals(0, arena.getStats().getScore2());

        Assertions.assertFalse(arenaController.isWon());
    }   

}
