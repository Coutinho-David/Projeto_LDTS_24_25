package pt.up.fe.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.gui.GUI;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.viewer.menu.EndMenuViewer;

import java.io.IOException;

class EndMenuViewerTest {
    private EndMenu endMenu;
    private GUI gui;
    private EndMenuViewer endMenuViewer;

    @BeforeEach
    void setUp() {
        endMenu = Mockito.mock(EndMenu.class);
        gui = Mockito.mock(GUI.class);

        endMenuViewer = new EndMenuViewer(endMenu);
    }

    @Test
    void testDrawElements() throws IOException {
        endMenuViewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawEndMenu(endMenu);
    }
}
