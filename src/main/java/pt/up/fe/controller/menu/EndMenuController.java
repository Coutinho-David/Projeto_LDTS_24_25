package pt.up.fe.controller.menu;

import pt.up.fe.Game;
import pt.up.fe.controller.Controller;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.arena.ArenaBuilder;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.states.ArenaState;
import pt.up.fe.states.MenuState;
import pt.up.fe.states.State;

import java.util.Objects;

public class EndMenuController extends Controller<EndMenu> {
    public EndMenuController(EndMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time){
        switch (action) {
            case UP :
                getModel().previous();
                break;
            case DOWN :
                getModel().next();
                break;
            case ENTER :
                if (Objects.equals(getModel().getOption(), "REMATCH")) {
                    int width = game.getGui().terminalWidth();
                    int height = game.getGui().terminalHeight();
                    Arena arena = new ArenaBuilder().createArena(width, height);
                    arena.setSeriesLength(getModel().getSeriesLength());
                    State<Arena> updated = new ArenaState(arena);
                    game.setState(updated);
                }

                if (Objects.equals(getModel().getOption(), "MAIN MENU")) {
                    game.setState(new MenuState(new Menu()));
                    break;
                }
                if (Objects.equals(getModel().getOption(), "EXIT") ) {
                    game.setState(null);
                    break;
                }
                break;
        }
    }
}
