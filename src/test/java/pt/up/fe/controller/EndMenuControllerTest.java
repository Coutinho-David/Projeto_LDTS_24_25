package pt.up.fe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.Game;
import pt.up.fe.controller.menu.EndMenuController;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.states.ArenaState;
import pt.up.fe.states.MenuState;

public class EndMenuControllerTest {

    private EndMenuController controller;
    private EndMenu endMenu;
    private Game game;
    private GUI gui;

    @BeforeEach
    void setUp() {
        endMenu = Mockito.mock(EndMenu.class);
        controller = new EndMenuController(endMenu);
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);


        Mockito.when(game.getGui()).thenReturn(gui);
        Mockito.when(gui.terminalWidth()).thenReturn(80);
        Mockito.when(gui.terminalHeight()).thenReturn(24);
    }

    @Test
    void stepUp() {
        controller.step(game, GUI.ACTION.UP, 0);
        Mockito.verify(endMenu).previous();
    }

    @Test
    void stepDown() {
        controller.step(game, GUI.ACTION.DOWN, 0);
        Mockito.verify(endMenu).next();
    }

    @Test
    void Rematch() {
        Mockito.when(endMenu.getOption()).thenReturn("REMATCH");

        controller.step(game, GUI.ACTION.ENTER, 0);

        Mockito.verify(game).setState(Mockito.any(ArenaState.class));
    }

    @Test
    void MainMenu() {
        Mockito.when(endMenu.getOption()).thenReturn("MAIN MENU");

        controller.step(game, GUI.ACTION.ENTER, 0);

        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    void Exit() {
        Mockito.when(endMenu.getOption()).thenReturn("EXIT");

        controller.step(game, GUI.ACTION.ENTER, 0);

        Mockito.verify(game).setState(null);
    }
}
