package pt.up.fe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.Game;
import pt.up.fe.controller.menu.InstructionsController;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.states.MenuState;

public class InstructionsControllerTest {

    private InstructionsController controller;
    private Menu menu;
    private Game game;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(Menu.class);
        controller = new InstructionsController(menu);
        game = Mockito.mock(Game.class);
    }

    @Test
    void TransitionToMenuState() {
        controller.step(game, GUI.ACTION.ENTER, 0);

        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    void DoNothing() {
        controller.step(game, GUI.ACTION.UP, 0);
        controller.step(game, GUI.ACTION.DOWN, 0);

        Mockito.verify(game, Mockito.never()).setState(Mockito.any());
    }
}
