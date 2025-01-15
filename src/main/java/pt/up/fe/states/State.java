package pt.up.fe.states;

import pt.up.fe.Game;
import pt.up.fe.controller.Controller;
import pt.up.fe.gui.GUI;
import pt.up.fe.viewer.Viewer;
import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }



    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}