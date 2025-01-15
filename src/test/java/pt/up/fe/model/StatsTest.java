package pt.up.fe.model;

import org.junit.jupiter.api.Test;
import pt.up.fe.model.game.Stats;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsTest {

    Stats stats = new Stats();

    @Test
    public void testGetScore1() {
        assertEquals(0, stats.getScore1());
    }

    @Test
    public void testSetScore1() {
        stats.setScore1();
        assertEquals(1, stats.getScore1());
    }

    @Test
    public void getScore2() {
        assertEquals(0, stats.getScore2());
    }

    @Test
    public void setScore2() {
        stats.setScore2();
        assertEquals(1, stats.getScore2());
    }

    @Test
    public void getWins1Count() {
        assertEquals(0, stats.getWins1Count());
    }

    @Test
    public void setWins1Count() {
        stats.setWins1Count();
        assertEquals(1, stats.getWins1Count());
    }

    @Test
    public void getWins2Count() {
        assertEquals(0, stats.getWins2Count());
    }

    @Test
    public void setWins2Count() {
        stats.setWins2Count();
        assertEquals(1, stats.getWins2Count());
    }

    @Test
    public void resetScores() {
        stats.resetScores();
        assertEquals(0, stats.getScore1());
        assertEquals(0, stats.getScore2());
    }

    @Test
    public void resetStats(){
        stats.setScore1();
        stats.setScore2();
        stats.setWins1Count();
        stats.setWins2Count();

        stats.resetStats();

        assertEquals(0, stats.getScore1());
        assertEquals(0, stats.getScore2());
        assertEquals(0, stats.getWins1Count());
        assertEquals(0, stats.getWins2Count());
    }
}
