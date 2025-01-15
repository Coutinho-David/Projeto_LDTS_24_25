package pt.up.fe.viewer.menu;

import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.viewer.Viewer;

import java.io.IOException;

public class EndMenuViewer extends Viewer<EndMenu> {

    public EndMenuViewer(EndMenu model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        EndMenu endMenu = getModel();
        gui.drawEndMenu(endMenu);
    }

    private EndMenu getModel() {
        return super.model;
    }
}