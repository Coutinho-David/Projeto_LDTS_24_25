package pt.up.fe.controller.game;

import pt.up.fe.Game;
import pt.up.fe.controller.Controller;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.game.elements.PowerUps.DoublePointsPowerUP;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.states.EndMenuState;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.elements.PowerUp;
import pt.up.fe.model.game.elements.PowerUpFactory;

import java.util.List;
import java.util.Random;

public class ArenaController extends Controller<Arena> {

    public ArenaController(Arena model) {
        super(model);
    }

    Arena arena = getModel();
    Player player_1 = arena.getPlayerOne();
    Player player_2 = arena.getPlayerTwo();
    Ball ball = arena.getBall();
    int seriesLength = arena.getSeriesLength();
    boolean won = false; //Controls the flow of the game, once won is set to true, game ends and end screen is loaded
    String winner = ""; //Used to pass the winner to end screen
    int ballSpeed = 1;
    List<PowerUp> activePowerUps = arena.getActivePowerUps();
    private long gameTime = System.currentTimeMillis();
    private int touch = 0; //Used to control PowerUp effect, 0 if the left player was the last to touch the ball, 1 if it was the right one

    @Override
    public void step(Game game, GUI.ACTION action, long time){
        if (won) {
            EndMenu endMenu = new EndMenu();
            endMenu.setWinner(winner);
            endMenu.setSeriesLength(seriesLength);
            game.setState(new EndMenuState(endMenu));
        }

        if (arena.getCountDown() > 0) {
            if (System.currentTimeMillis() - gameTime >= 1000) {
                arena.subCountDown();
                gameTime = System.currentTimeMillis();
                arena.setLastPowerUpTime();
            }
        } else { //Game is frozen until this point
            if (arena.getGameTime() <= 0) {
                tieBreaker();
            }

            switch (action) {
                case PLAYER1UP -> {
                    if (arena.getPlayerOne().isReversed()) {
                        player_1.moveDown(arena.getHeight());
                    } else {
                        player_1.moveUp();
                    }
                }
                case PLAYER1DOWN -> {
                    if (arena.getPlayerOne().isReversed()) {
                        player_1.moveUp();
                    } else {
                        player_1.moveDown(arena.getHeight());
                    }
                }
                case PLAYER2UP -> {
                    if (arena.getPlayerTwo().isReversed()) {
                        player_2.moveDown(arena.getHeight());
                    } else {
                        player_2.moveUp();
                    }
                }
                case PLAYER2DOWN -> {
                    if (arena.getPlayerTwo().isReversed()) {
                        player_2.moveUp();
                    } else {
                        player_2.moveDown(arena.getHeight());
                    }
                }

            }

            ball.move();
            checkCollisions();

            //Spawn PowerUp evey 10 secs
            if (System.currentTimeMillis() - arena.getLastPowerUpTime() >= 10000) {
                spawnPowerUps();
                arena.setLastPowerUpTime();
            }

            //Match timer control, only integer updates
            if (System.currentTimeMillis() - gameTime >= 1000) {
                arena.subGameTime();
                gameTime = System.currentTimeMillis();
            }
        }
    }

    public void checkCollisions() {
        if ((ball.getX() == player_1.getX() + 1) && (ball.getY() >= player_1.getY() && ball.getY() <= player_1.getY() + player_1.getSize()-1)) {
            ball.reverseX();
            ball.setX(player_1.getX() + 1);
            if (ball.isWeird()){
                BallAngle();
            }
            ball.normalizeSpeed(ballSpeed);
            touch = 0;
            if (ball.getVelocityX() == 0) ball.setVelocities(1, ball.getVelocityY());
        }
        if  ((ball.getX() == player_2.getX() - 1) && (ball.getY() >= player_2.getY() && ball.getY() <= player_2.getY() + player_2.getSize()-1)) {
            ball.reverseX();
            ball.setX(player_2.getX() - 1);
            if (ball.isWeird()){
                BallAngle();
            }
            ball.normalizeSpeed(ballSpeed);
            touch = 1;
            if (ball.getVelocityX() == 0) ball.setVelocities(-1, ball.getVelocityY());
        }

        if (ball.getY() <= 1 || ball.getY() >= arena.getHeight() - 2) {
            ball.reverseY();
        }

        // Reset if ball goes out of bounds
        if (ball.getX() <= 0 || ball.getX() >= arena.getWidth() - 1) {
            goal();

        }

        PowerUp toRemove = null;

        for (PowerUp powerUp : activePowerUps) {
            Position powerUpPosition = powerUp.getPosition();
            if ((ball.getX() <= powerUpPosition.getX() + 2 && ball.getX() >= powerUpPosition.getX() - 2)
                    && (ball.getY() <= powerUpPosition.getY() + 2 && ball.getY() >= powerUpPosition.getY() - 2)) {
                powerUp.activate(arena, touch);
                toRemove = powerUp;
            }
        }

        if (toRemove != null) {
            activePowerUps.remove(toRemove);
        }

    }

    public void goal() {
        //Check which player scored
        if (ball.getX() <= 0) {
            if (DoublePointsPowerUP.isActive()) {
                arena.getStats().setScore2();
                arena.getStats().setScore2(); //Double the score if power-up is active
            } else {
                arena.getStats().setScore2(); //Regular score increment
            }
        } else {
            if (DoublePointsPowerUP.isActive()) {
                arena.getStats().setScore1();
                arena.getStats().setScore1(); //Double the score if power-up is active
            } else {
                arena.getStats().setScore1(); //Regular score increment
            }
        }

        //Check if match has been won
        matchWin();

        //Reset the moving parts when a goal is scored for continuing the match
        resetPlayerOne();
        resetPlayerTwo();
        resetBall();
    }

    public void matchWin() {
        //When a player scores 5 goals, that match is over and everything is reset, the series will progress until one player has enough wins
        if (arena.getStats().getScore1() >= 5) {
            arena.getStats().setWins1Count();
            arena.getStats().resetScores();
            arena.resetGameTime();
            arena.resetActivePowerUps();
            resetPlayerOne();
            resetPlayerTwo();
            resetBall();
            arena.resetCountDown();
            seriesWin(); //Check if series has been won

        } else if (arena.getStats().getScore2() >= 5) {
            arena.getStats().setWins2Count();
            arena.getStats().resetScores();
            arena.resetGameTime();
            arena.resetActivePowerUps();
            resetPlayerOne();
            resetPlayerTwo();
            resetBall();
            arena.resetCountDown();
            seriesWin(); //Checking is done inside the if statements because it was inefficient to check if it wasn't necessary
        }
    }

    public void seriesWin() {
        //Logic to determine when to end the series, sets the winner
        if (arena.getStats().getWins1Count() == seriesLength/2.0 + 0.5) {
            arena.getStats().resetStats();
            this.winner = "PLAYER_1";
            won = true;
        } else if (arena.getStats().getWins2Count() == seriesLength/2.0 + 0.5) {
            arena.getStats().resetStats();
            this.winner = "PLAYER_2";
            won = true;
        }
    }

    public void tieBreaker() {
        //Once time reaches 0 the first to outscore wins, immediate or not (golden goal)
        if (arena.getStats().getScore1() > arena.getStats().getScore2()) {
            arena.getStats().setWins1Count();
            arena.getStats().resetScores();
            arena.resetGameTime();
            arena.resetActivePowerUps();
            resetPlayerOne();
            resetPlayerTwo();
            resetBall();
            arena.resetCountDown();
            seriesWin();
        } else if (arena.getStats().getScore2() > arena.getStats().getScore1()) {
            arena.getStats().setWins2Count();
            arena.getStats().resetScores();
            arena.resetGameTime();
            arena.resetActivePowerUps();
            resetPlayerOne();
            resetPlayerTwo();
            resetBall();
            arena.resetCountDown();
            seriesWin();
        }
    }

    public void BallAngle() {
        Random random = new Random();

        int angle = random.nextInt(5) - 2;
        int randomness = random.nextInt(3) - 1;

        angle += randomness; // de -3 a 3

        ball.setVelocities(ball.getVelocityX(), angle);
    }

    public void resetBall() {
        Random random = new Random();
        
        ball.setX(arena.getWidth() / 2 - 1);
        ball.setY(arena.getHeight() / 2);
        //randomize the player and angle that the ball goes to
        int velocityX = random.nextBoolean() ? 1 : -1;
        int velocityY = random.nextBoolean() ? 1 : -1;
        ball.setVelocities(velocityX, velocityY);
    }

    public void resetPlayerOne() {
        player_1.setX(4);
        player_1.setY(arena.getHeight()/2 - 3);
    }

    public void resetPlayerTwo() {
        player_2.setX(arena.getWidth() - 5);
        player_2.setY(arena.getHeight()/2 - 3);
    }

    public void spawnPowerUps() {
        PowerUpFactory powerUpFactory = new PowerUpFactory(arena);
        PowerUp newPowerUp = powerUpFactory.createRandomPowerUP();
        if (!activePowerUps.contains(newPowerUp)) {
            activePowerUps.add(newPowerUp);
        }
    }


    public String getWinner() {
        return winner;
    }

    public boolean isWon() {
        return won;
    }
}