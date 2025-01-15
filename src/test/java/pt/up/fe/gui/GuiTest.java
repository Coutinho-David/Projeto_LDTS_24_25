package pt.up.fe.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.model.menu.Menu;

import java.io.IOException;
import java.util.List;


public class GuiTest {

    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics graphics;

    @BeforeEach
    public void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(200, 200));
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        gui = new LanternaGUI(screen, graphics);
    }

    @Test
    void testClear() {
        gui.clear();

        Mockito.verify(screen).clear();
    }

    @Test
    void testRefresh() throws IOException {
        gui.refresh();

        Mockito.verify(screen).refresh();
    }

    @Test
    void testClose() throws IOException {
        gui.close();

        Mockito.verify(screen).close();
    }

    @Test
    void testGetActionMenuState() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp, false, false)).thenReturn(new KeyStroke(KeyType.ArrowDown, false, false)).thenReturn(new KeyStroke(KeyType.Enter, false, false));

        gui.setState(0);
        Assertions.assertEquals(GUI.ACTION.UP, gui.getAction());
        Assertions.assertEquals(GUI.ACTION.DOWN, gui.getAction());
        Assertions.assertEquals(GUI.ACTION.ENTER, gui.getAction());
    }

    @Test
    void testGetActionGameState() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('w', false, false)).thenReturn(new KeyStroke('s', false, false)).thenReturn(new KeyStroke(KeyType.ArrowUp, false, false)).thenReturn(new KeyStroke(KeyType.ArrowDown, false, false));

        gui.setState(1);
        Assertions.assertEquals(GUI.ACTION.PLAYER1UP, gui.getAction());
        Assertions.assertEquals(GUI.ACTION.PLAYER1DOWN, gui.getAction());
        Assertions.assertEquals(GUI.ACTION.PLAYER2UP, gui.getAction());
        Assertions.assertEquals(GUI.ACTION.PLAYER2DOWN, gui.getAction());
    }

    @Test
    void testDrawInstructions() {
        Menu menu = Mockito.mock(Menu.class);

        gui.drawInstructions(menu);

        Mockito.verify(graphics, Mockito.atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString("#898989"));
        Mockito.verify(graphics, Mockito.atLeastOnce()).putString(Mockito.any(TerminalPosition.class), Mockito.anyString());
        Mockito.verify(graphics, Mockito.atLeastOnce()).setForegroundColor(TextColor.Factory.fromString("BLACK"));
    }

    @Test
    void testDrawEndMenu() throws IOException {
        EndMenu endMenu = Mockito.mock(EndMenu.class);
        Mockito.when(endMenu.getWinner()).thenReturn("PLAYER_1");
        Mockito.when(endMenu.getOptions()).thenReturn(List.of("Retry", "Quit"));
        Mockito.when(endMenu.getSelected()).thenReturn(0);

        gui.drawEndMenu(endMenu);

        Mockito.verify(graphics, Mockito.atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString("#898989"));
        Mockito.verify(graphics, Mockito.atLeastOnce()).putString(Mockito.any(TerminalPosition.class), Mockito.anyString());
        Mockito.verify(graphics, Mockito.atLeastOnce()).setForegroundColor(TextColor.Factory.fromString("#81c1ff"));
    }

    @Test
    void testDrawPowerUp() {
        gui.drawPowerUp(10, 10, "#FF0000");

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.atLeastOnce()).putString(Mockito.any(TerminalPosition.class), Mockito.anyString());
    }

    @Test
    void testTerminalSize() {
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(10, 10));
        Assertions.assertEquals(10, gui.terminalWidth());
        Assertions.assertEquals(10, gui.terminalHeight());
    }

    @Test
    void testPowerUpText() {
        Arena arenaMock = Mockito.mock(Arena.class);
        Mockito.when(arenaMock.getPowerUpCaught()).thenReturn("PowerUp1");
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(20, 10));

        gui.powerUpText(arenaMock, 1000L);

        Mockito.verify(graphics).putString(Mockito.any(TerminalPosition.class), Mockito.eq("Activated PowerUp1"));
    }

    @Test
    void testGetActionMenuStateEOF() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.EOF, false, false));

        gui.setState(0);
        Assertions.assertEquals(GUI.ACTION.QUIT, gui.getAction());
    }

    @Test
    void testGetActionGameState_InvalidKey() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('a', false, false)); // Invalid key

        gui.setState(1);
        Assertions.assertEquals(GUI.ACTION.NONE, gui.getAction());
    }

    @Test
    void testDrawMenu() throws IOException {
        Menu menu = Mockito.mock(Menu.class);
        Mockito.when(menu.getSeriesLength()).thenReturn(5);
        Mockito.when(menu.getOptions()).thenReturn(List.of("Start", "Settings", "Exit"));
        Mockito.when(menu.getSelected()).thenReturn(0);

        gui.drawMenu(menu);

        Mockito.verify(graphics, Mockito.atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString("#898989"));
        Mockito.verify(graphics, Mockito.atLeastOnce()).putString(Mockito.any(TerminalPosition.class), Mockito.anyString());
    }

    @Test
    void testDrawPlayer() {
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getX()).thenReturn(10);
        Mockito.when(player.getY()).thenReturn(20);
        Mockito.when(player.getSize()).thenReturn(5);

        gui.drawPlayer(player);

        Mockito.verify(graphics, Mockito.times(5)).putString(Mockito.any(TerminalPosition.class), Mockito.eq("█"));
    }

}
