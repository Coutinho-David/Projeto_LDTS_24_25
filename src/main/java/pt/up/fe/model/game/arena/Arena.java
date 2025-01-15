package pt.up.fe.model.game.arena;

import pt.up.fe.model.game.Stats;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.game.elements.PowerUp;
import java.util.List;

public class Arena {
    private static int height, width;
    private final List<Player> players;
    private final Ball ball;
    Stats stats;
    private int seriesLength;
    private int gameTime = 120;
    private int countDown = 3;
    private List<PowerUp> activePowerUps;
    private long lastPowerUpTime = System.currentTimeMillis();
    private String powerUpCaught = "";
    private long powerUpCaughtTime = System.currentTimeMillis();

    public Arena(int width, int height, List<Player> players, Ball ball, Stats stats, int seriesLength, List<PowerUp> activePowerUps) {
        Arena.width = width;
        Arena.height = height;
        this.players = players;
        this.ball = ball;
        this.stats = stats;
        this.seriesLength = seriesLength;
        this.activePowerUps = activePowerUps;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Ball getBall() {
        return this.ball;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerOne() {
        return players.getFirst();
    }

    public Player getPlayerTwo() {
        return players.getLast();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Stats getStats() {
        return this.stats;
    }

    public int getSeriesLength() {
        return seriesLength;
    }

    public void setSeriesLength(int seriesLength) {
        this.seriesLength = seriesLength;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void subGameTime() {
        this.gameTime--;
    }

    public void resetGameTime() {
        this.gameTime = 120;
    }

    public int getCountDown() {
        return countDown;
    }

    public void resetCountDown() {
        this.countDown = 3;
    }

    public void subCountDown() {
        this.countDown--;
    }

    public List<PowerUp> getActivePowerUps() {
        return activePowerUps;
    }

    public void setActivePowerUps(List<PowerUp> activePowerUps) { this.activePowerUps = activePowerUps;}

    public void resetActivePowerUps() {this.activePowerUps.clear();}

    public long getLastPowerUpTime() {
        return lastPowerUpTime;
    }

    public void setLastPowerUpTime() {
        this.lastPowerUpTime = System.currentTimeMillis();
    }

    public void setPowerUpCaught(String powerUpCaught) {
        this.powerUpCaught = powerUpCaught;
    }

    public String getPowerUpCaught() {
        return powerUpCaught;
    }

    public void resetPowerUpCaught() {
        this.powerUpCaught = "";
    }

    public long getPowerUpCaughtTime() {
        return powerUpCaughtTime;
    }

    public void setPowerUpCaughtTime(long powerUpCaughtTime) {
        this.powerUpCaughtTime = powerUpCaughtTime;
    }
}