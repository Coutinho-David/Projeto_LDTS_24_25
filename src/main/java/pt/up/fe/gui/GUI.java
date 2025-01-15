package pt.up.fe.gui;

import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.model.menu.Menu;
import java.io.IOException;

public interface GUI {

    ACTION getAction() throws IOException;

    void drawMenu(Menu menu) throws IOException;
    void drawInstructions(Menu menu);
    void drawEndMenu(EndMenu endMenu) throws IOException;
    void drawTextMenu(Position position, String text, String color);
    void drawImage(String file,Position position) throws IOException;

    void drawArena(Arena arena);
    void drawTextArena(Position position, String text);
    void drawPlayer(Player player);
    void drawBall(Ball ball);
    void drawPowerUp(int x, int y, String color);
    void powerUpText(Arena arena, long timer);

    int terminalWidth();
    int terminalHeight();
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    enum ACTION {UP, DOWN, PAUSE, ENTER, QUIT, NONE, PLAYER1UP, PLAYER1DOWN, PLAYER2UP, PLAYER2DOWN, REMATCH, NEW_GAME}

}

