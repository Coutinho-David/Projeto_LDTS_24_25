package pt.up.fe.viewer.menu;

import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.viewer.Viewer;

import java.io.IOException;


public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        Menu menu = getModel();
        gui.drawMenu(menu);
    }

    private Menu getModel() {
        return super.model;
    }
}
