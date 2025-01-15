package pt.up.fe;

import pt.up.fe.gui.GUI;
import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.states.State;
import pt.up.fe.states.MenuState;
import java.io.IOException;

public class Game {

    private State<?> state;
    private final LanternaGUI gui;

    public Game() throws IOException {
        this.gui = new LanternaGUI();
        this.state = new MenuState(new Menu());
    }

    public void run() throws IOException {
        int FPS = 45;
        int frameTime = 1000 / FPS;
        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {}
        }
        gui.close();
    }

    public void setState(State<?> o) {
        this.state = o;
    }

    public GUI getGui() {
        return gui;
    }



}