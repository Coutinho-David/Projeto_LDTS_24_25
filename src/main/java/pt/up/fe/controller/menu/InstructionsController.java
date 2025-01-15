package pt.up.fe.controller.menu;

import pt.up.fe.Game;
import pt.up.fe.controller.Controller;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.states.MenuState;
import java.util.Objects;

public class InstructionsController extends Controller<Menu> {
    public InstructionsController(Menu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (Objects.requireNonNull(action) == GUI.ACTION.ENTER) {
            game.setState(new MenuState(new Menu()));
        }
    }
}
