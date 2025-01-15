package pt.up.fe.viewer;

import pt.up.fe.gui.GUI;
import java.io.IOException;

public abstract class Viewer <T>{
    protected final T model;

    protected Viewer(T model) {
        this.model = model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }

    protected abstract void drawElements(GUI gui) throws IOException;
}
