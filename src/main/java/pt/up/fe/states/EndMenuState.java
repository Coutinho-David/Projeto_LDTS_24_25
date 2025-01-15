package pt.up.fe.states;

import pt.up.fe.controller.Controller;
import pt.up.fe.controller.menu.EndMenuController;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.viewer.Viewer;
import pt.up.fe.viewer.menu.EndMenuViewer;

public class EndMenuState extends State<EndMenu> {
    public EndMenuState(EndMenu model) {
        super(model);
    }

    @Override
    protected Viewer<EndMenu> getViewer() {
        return new EndMenuViewer(getModel());
    }

    @Override
    protected Controller<EndMenu> getController() {
        return new EndMenuController(getModel());
    }
}