package pt.up.fe.controller.menu;

import pt.up.fe.Game;
import pt.up.fe.controller.Controller;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.arena.ArenaBuilder;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.states.ArenaState;
import pt.up.fe.states.InstructionsState;
import pt.up.fe.states.State;

import java.util.Objects;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time){
        switch (action) {
            case UP:
                getModel().previous();
                break;
            case DOWN:
                getModel().next();
                break;
            case ENTER:
                //Series length
                if (Objects.equals(getModel().getOption(), "1") || Objects.equals(getModel().getOption(), "3") || Objects.equals(getModel().getOption(), "5")) {
                    getModel().setSeriesLength(Integer.parseInt(getModel().getOption()));
                    break;

                }
                //Game start
                if (Objects.equals(getModel().getOption(), "START")) {
                    int width = game.getGui().terminalWidth();
                    int height = game.getGui().terminalHeight();
                    int series = getModel().getSeriesLength();
                    System.out.println(series);
                    Arena arena = new ArenaBuilder().createArena(width, height);
                    arena.setSeriesLength(series);
                    State<Arena> updated = new ArenaState(arena);
                    game.setState(updated);
                }
                //Instructions
                if (Objects.equals(getModel().getOption(), "INSTRUCTIONS")) {
                    State<Menu> instructionsState= new InstructionsState(getModel());
                    game.setState(instructionsState);
                }
                //Exit
                if (Objects.equals(getModel().getOption(), "EXIT") ) {
                    game.setState(null);
                    break;
                }
                break;
        }
    }
}
