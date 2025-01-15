package pt.up.fe.viewer.menu;

import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.viewer.Viewer;

public class InstructionsViewer extends Viewer<Menu> {

    public InstructionsViewer(Menu model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui)  {
        Menu menu = getModel();
        gui.drawInstructions(menu);
    }

    private Menu getModel() {
        return super.model;
    }
}
