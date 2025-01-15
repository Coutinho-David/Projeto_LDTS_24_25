package pt.up.fe.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.Menu;
import pt.up.fe.viewer.menu.InstructionsViewer;
import pt.up.fe.viewer.menu.MenuViewer;

import java.io.IOException;

public class InstructionsViewerTest {
    private Menu menu;
    private GUI gui;
    private InstructionsViewer instructionsViewer;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(Menu.class);
        gui = Mockito.mock(GUI.class);

        instructionsViewer = new InstructionsViewer(menu);
    }

    @Test
    void testDrawElements() throws IOException {
        instructionsViewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawInstructions(menu);
    }
}
