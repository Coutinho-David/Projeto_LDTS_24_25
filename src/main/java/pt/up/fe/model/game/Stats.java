package pt.up.fe.model.game;

public class Stats {
    private int score1;
    private int score2;
    private int wins1Count;
    private int wins2Count;

    public Stats() {
        score1 = 0;
        score2 = 0;
        wins1Count = 0;
        wins2Count = 0;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1() {
        this.score1++;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2() {
        this.score2++;
    }

    public int getWins1Count() {
        return wins1Count;
    }

    public void setWins1Count() {
        this.wins1Count++;
    }

    public int getWins2Count() {
        return wins2Count;
    }

    public void setWins2Count() {
        this.wins2Count++;
    }

    public void resetScores() {
        this.score1 = 0;
        this.score2 = 0;
    }

    public void resetStats(){
        this.score1 = 0;
        this.score2 = 0;
        this.wins1Count = 0;
        this.wins2Count = 0;
    }
}