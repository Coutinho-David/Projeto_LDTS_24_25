package pt.up.fe.states;

import pt.up.fe.controller.Controller;
import pt.up.fe.controller.game.ArenaController;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.viewer.Viewer;
import pt.up.fe.viewer.game.ArenaViewer;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new ArenaViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

}