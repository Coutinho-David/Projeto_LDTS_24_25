package pt.up.fe.states;

import pt.up.fe.controller.Controller;
import pt.up.fe.controller.menu.InstructionsController;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.viewer.Viewer;
import pt.up.fe.viewer.menu.InstructionsViewer;

public class InstructionsState extends State<Menu> {
    public InstructionsState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new InstructionsViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new InstructionsController(getModel());
    }
}
