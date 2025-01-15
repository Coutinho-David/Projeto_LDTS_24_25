package pt.up.fe.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.gui.GUI;
import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.game.elements.PowerUp;
import pt.up.fe.viewer.game.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ArenaViewerTest {

    private Arena arena;
    private GUI gui;
    private ArenaViewer arenaViewer;

    @BeforeEach
    void setUp() throws IOException {
        arena = Mockito.mock(Arena.class);
        gui = Mockito.mock(LanternaGUI.class);
        arenaViewer = new ArenaViewer(arena);
    }

    @Test
    void testDraw() throws IOException {
        arenaViewer.draw(gui);

        Mockito.verify(gui, Mockito.times(2)).clear();
        Mockito.verify(gui, Mockito.times(2)).refresh();
    }

    @Test
    void testDrawElements() throws IOException {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        PowerUp powerUp1 = Mockito.mock(PowerUp.class);
        PowerUp powerUp2 = Mockito.mock(PowerUp.class);
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(powerUp1);
        powerUps.add(powerUp2);

        Mockito.when(arena.getPlayers()).thenReturn(players);
        Mockito.when(arena.getActivePowerUps()).thenReturn(powerUps);
        Mockito.when(arena.getPowerUpCaught()).thenReturn("SpeedBoost");
        Mockito.when(arena.getPowerUpCaughtTime()).thenReturn(10L);

        arenaViewer.drawElements(gui);

        Mockito.verify(gui).clear();
        Mockito.verify(gui).drawArena(arena);
        Mockito.verify(gui).drawPlayer(player1);
        Mockito.verify(gui).drawPlayer(player2);
        for (PowerUp powerUp : powerUps) {
            Mockito.verify(powerUp).draw(Mockito.any());
        }

        Mockito.verify(gui).drawBall(arena.getBall());
        Mockito.verify(gui).powerUpText(arena, 10);
        Mockito.verify(gui).refresh();
    }
}
