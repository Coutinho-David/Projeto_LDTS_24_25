package pt.up.fe.states;

import pt.up.fe.controller.Controller;
import pt.up.fe.controller.menu.MenuController;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.viewer.Viewer;
import pt.up.fe.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}