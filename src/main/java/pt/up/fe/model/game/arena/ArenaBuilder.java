package pt.up.fe.model.game.arena;

import pt.up.fe.model.game.Stats;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.game.elements.PowerUp;

import java.util.ArrayList;
import java.util.List;

public class ArenaBuilder {
    public Arena createArena(int width, int height) {
        List<Player> players = new ArrayList<>();
        players.add(new Player(4,height/2 - 3));
        players.add(new Player(width - 5,height/2 - 3));
        Ball ball = new Ball(width/2 - 1, height/2 - 1);
        Stats stats = new Stats();
        int seriesLength = 0;
        List<PowerUp > activePowerUps = new ArrayList<>();
        return new Arena(width, height,players,ball, stats, seriesLength, activePowerUps);
    }
}
