package pt.up.fe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.Game;
import pt.up.fe.controller.menu.MenuController;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.states.ArenaState;
import pt.up.fe.states.InstructionsState;

public class MenuControllerTest {
    private MenuController controller;
    private Menu menu;
    private Game game;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(Menu.class);
        controller = new MenuController(menu);
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);


        Mockito.when(game.getGui()).thenReturn(gui);
        Mockito.when(gui.terminalWidth()).thenReturn(80);
        Mockito.when(gui.terminalHeight()).thenReturn(24);
    }

    @Test
    void testNavigateUp() {
        controller.step(game, GUI.ACTION.UP, 0);
        Mockito.verify(menu).previous();
    }

    @Test
    void testNavigateDown() {
        controller.step(game, GUI.ACTION.DOWN, 0);
        Mockito.verify(menu).next();
    }

    @Test
    void testSetSeriesLength() {
        Mockito.when(menu.getOption()).thenReturn("1");
        controller.step(game, GUI.ACTION.ENTER, 0);
        Mockito.verify(menu).setSeriesLength(1);

        Mockito.when(menu.getOption()).thenReturn("3");
        controller.step(game, GUI.ACTION.ENTER, 0);
        Mockito.verify(menu).setSeriesLength(3);

        Mockito.when(menu.getOption()).thenReturn("5");
        controller.step(game, GUI.ACTION.ENTER, 0);
        Mockito.verify(menu).setSeriesLength(5);
    }

    @Test
    void testStartGame() {
        Mockito.when(menu.getOption()).thenReturn("START");
        Mockito.when(menu.getSeriesLength()).thenReturn(3);
        controller.step(game, GUI.ACTION.ENTER, 0);
        Mockito.verify(game).setState(Mockito.any(ArenaState.class));
    }

    @Test
    void testInstructionsOption() {
        Mockito.when(menu.getOption()).thenReturn("INSTRUCTIONS");
        controller.step(game, GUI.ACTION.ENTER, 0);
        Mockito.verify(game).setState(Mockito.any(InstructionsState.class));
    }

    @Test
    void testExitOption() {
        Mockito.when(menu.getOption()).thenReturn("EXIT");
        controller.step(game, GUI.ACTION.ENTER, 0);
        Mockito.verify(game).setState(null);
    }
}
