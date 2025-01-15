package pt.up.fe.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Ball;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.menu.EndMenu;
import pt.up.fe.model.menu.Menu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class LanternaGUI implements GUI {
    private final Screen screen;
    private final TextGraphics textGraphics;
    private int state; // 0 for menu, instructions and post-match screen, 1 for game;

    public LanternaGUI(Screen screen, TextGraphics textGraphics) {
        this.screen = screen;
        this.textGraphics = textGraphics;
    }

    public LanternaGUI() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(120, 30)).createTerminal();
        this.screen = createScreen(terminal);
        this.textGraphics = screen.newTextGraphics();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    @Override
    public ACTION getAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (state == 0) {
            if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
            if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
            if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.ENTER;
        } else if (state == 1) {
            if (keyStroke.getKeyType() == KeyType.Character && (keyStroke.getCharacter() == 'w'||keyStroke.getCharacter() == 'W')) return ACTION.PLAYER1UP;
            if (keyStroke.getKeyType() == KeyType.Character && (keyStroke.getCharacter() == 's'||keyStroke.getCharacter() == 'S')) return ACTION.PLAYER1DOWN;
            if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.PLAYER2UP;
            if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.PLAYER2DOWN;

        }
        return ACTION.NONE;
    }

    @Override
    public void drawMenu(Menu menu) throws IOException {
        state = 0;
        int width = terminalWidth();
        int height = terminalHeight();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#898989"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        String p1 = "PLAYER_1";
        String color = "#81c1ff";
        Position position = new Position ((terminalWidth() - p1.length()) / 4, terminalHeight() / 2 + 5);
        drawTextMenu(position,p1,color);
        String p2 = "PLAYER_2";
        color = "#ffbd49";
        position = new Position (3*(terminalWidth() - p1.length()) / 4, terminalHeight() / 2 + 5);
        drawTextMenu(position,p2,color);
        int seriesLength = menu.getSeriesLength();
        color = "BLACK";

        String series = "SERIES LENGTH: " + seriesLength;
        position = new Position ((terminalWidth() - series.length()) / 2, terminalHeight() / 2 + 4);
        drawTextMenu(position, series, color);

        for (int i = 0; i < menu.getOptions().size(); i++) {
            int selected = menu.getSelected();
            String option = menu.getOptions().get(i);
            if (Objects.equals(option, "1") || Objects.equals(option, "3") || Objects.equals(option, "5")) {
                color = (i == selected ? "WHITE" : "BLACK");
                position = new Position(
                        (terminalWidth() - option.length()) / 2, terminalHeight() / 2 + (i + 1) + 4);
                drawTextMenu(position, option, color);
            } else {
                color = (i == selected ? "WHITE" : "BLACK");
                position = new Position(
                        (terminalWidth() - option.length()) / 2, terminalHeight() / 2 + (i + 2) + 4);
                drawTextMenu(position, option, color);
            }
            position = new Position ((width-30)/2, 5);
            drawImage("docs/resources/logoMenu.png", position);

        }
    }

    @Override
    public void drawInstructions(Menu menu) {
        state = 0;
        int width = terminalWidth();
        int height = terminalHeight();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#898989"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        Position position = new Position ((terminalWidth() - 12)/2,2 );
        String color = "BLACK";

        String text = "INSTRUCTIONS";
        drawTextMenu(position, text, color);

        position = new Position (5, 4);
        text = "PLAYER MOVEMENT: ";
        drawTextMenu(position, text, color);

        position = new Position (22, 4);
        text = "PLAYER_1 MOVES WITH W/S";
        drawTextMenu(position, text, color);

        position = new Position (22, 5);
        text = "PLAYER_2 MOVES WITH ↑/↓";
        drawTextMenu(position, text, color);

        position = new Position (5, 7);
        text = "SERIES END - ONE PLAYER WINS MORE THAN HALF OF THE GAMES IN THE SERIES";
        drawTextMenu(position, text, color);

        position = new Position (5, 9);
        text = "GAME WIN CRITERIA – 1. 5 GOALS";
        drawTextMenu(position, text, color);

        position = new Position (25, 10);
        text = "2. MOST GOALS WHEN TIME'S UP";
        drawTextMenu(position, text, color);

        position = new Position (25, 11);
        text = "3. OVERTIME GOAL";
        drawTextMenu(position, text, color);

        position = new Position (5, 13);
        text = "POWER UPS - FASTER BALL";
        drawTextMenu(position, text, color);

        position = new Position (17, 15);
        text = "INVISIBLE BALL";
        drawTextMenu(position, text, color);

        position = new Position (17, 14);
        text = "REVERSE CONTROLS";
        drawTextMenu(position, text, color);

        position = new Position (17, 16);
        text = "WEIRD BOUNCE";
        drawTextMenu(position, text, color);

        position = new Position (17, 17);
        text = "DOUBLE POINTS";
        drawTextMenu(position, text, color);

        position = new Position (17, 18);
        text = "SMALLER OPPONENT";
        drawTextMenu(position, text, color);

        position = new Position (17, 19);
        text = "BIGGER PLAYER";
        drawTextMenu(position, text, color);

        color = "WHITE";
        position = new Position ((terminalWidth() - 4)/2, terminalHeight() - 2);
        text = "EXIT";
        drawTextMenu(position, text, color);
    }

    @Override
    public void drawEndMenu(EndMenu endMenu) throws IOException {
        state = 0;
        int width = terminalWidth();
        int height = terminalHeight();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#898989"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        String color;
        String winner = endMenu.getWinner() + " WON";
        if (Objects.equals(endMenu.getWinner(),"PLAYER_1")) color = "#81c1ff";
        else color = "#ffbd49";
        Position position = new Position ((terminalWidth()-winner.length())/ 2, terminalHeight() / 2 + 3);
        drawTextMenu(position, winner, color);
        for (int i = 0; i < endMenu.getOptions().size(); i++) {
            int selected = endMenu.getSelected();
            String option = endMenu.getOptions().get(i);
            color = (i == selected ? "WHITE" : "BLACK");
            position = new Position(
                    (terminalWidth() - option.length()) / 2, terminalHeight() / 2 + (i + 2) + 5);
                drawTextMenu(position, option, color);
            }

        position = new Position ((width-30)/2, 5);
        drawImage("docs/resources/logoMenu.png", position);
        }

    @Override
    public void drawTextMenu(Position position, String text, String color) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#898989"));
        TextColor textColor = TextColor.Factory.fromString(color);
        textGraphics.setForegroundColor(textColor);
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), text);
    }

    @Override
    public void drawImage(String file, Position position) throws IOException {
        BufferedImage sprite = ImageIO.read(new File(file));
        for (int x = 0; x < sprite.getWidth(); x++){
            for (int y = 0; y < sprite.getHeight(); y++){
                int a = sprite.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    screen.setCharacter(position.getX() + x, position.getY() + y, c);
                }
            }
        }
    }

    @Override
    public void drawArena(Arena arena) {
        state = 1;
        int width = arena.getWidth();
        int height = arena.getHeight();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#232323"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        if (arena.getCountDown() > 0) {
            Position countDownPos = new Position((terminalWidth()-1)/2, terminalHeight()/2 - 3);
            String time = String.valueOf(arena.getCountDown());
            drawTextArena(countDownPos, time);
        }

        for (int col = 0; col < width; col++) {
            graphics.putString(new TerminalPosition(col, 0), "─"); // Top horizontal line
            graphics.putString(new TerminalPosition(col, height - 1), "─"); // Bottom horizontal line
        }
        for (int row = 1; row < height - 1; row++) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#81C1FF"));
            graphics.putString(new TerminalPosition(0, row), "█"); // Top horizontal line
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFBD49"));
            graphics.putString(new TerminalPosition(width - 1, row), "█");
        }

        String text = (arena.getStats().getScore1()) + "  -  " + (arena.getStats().getScore2());
        Position position = new Position (width/2 - 3, 4);
        drawTextArena(position, text);
        text = "Series: " + arena.getStats().getWins1Count() + " - " + arena.getStats().getWins2Count();
        position = new Position ((width-text.length())/2, 1);
        drawTextArena(position, text);
        if (arena.getGameTime() >= 0) {
            text = String.valueOf(arena.getGameTime());
        } else {
            text = "GOLDEN GOAL";
        }
        if (text.length()%2==0) {
            text = 0 + text;
        }
        position = new Position ((width - text.length()+1)/2 , 3);
        drawTextArena(position, text);
    }

    @Override
    public void drawTextArena(Position position, String text) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#232323"));
        TextColor textColor = TextColor.Factory.fromString("WHITE");
        textGraphics.setForegroundColor(textColor);
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), text);
    }

    @Override
    public void drawPlayer(Player player) {
        TextGraphics graphics = screen.newTextGraphics();
        int x = player.getX();
        int y = player.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        for (int i = 0; i < player.getSize(); i++) {
            graphics.putString(new TerminalPosition(x, y + i), "█");
        }
    }

    @Override
    public void drawBall(Ball ball) {
        TextGraphics graphics = screen.newTextGraphics();
        if (ball.isInvisible()) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#2C2828"));
        }
        else {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        }
        graphics.setBackgroundColor(TextColor.Factory.fromString("#232023"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(ball.getX(), ball.getY()), "●");
    }

    @Override
    public void drawPowerUp(int x, int y, String color) {
        TextGraphics PowerUpGui = screen.newTextGraphics();

        char[][] sprite = {
                {'◜', ' ', '◝'},
                {'◟', ' ', '◞'}
        };

        TextColor textColor = TextColor.Factory.fromString(color);

        PowerUpGui.setForegroundColor(textColor);
        PowerUpGui.enableModifiers(SGR.BOLD);

        PowerUpGui.setBackgroundColor(TextColor.Factory.fromString("#232323")); // Background equal to the arena

        for (int row = 0; row < sprite.length; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < sprite[row].length; col++) {
                line.append(sprite[row][col]);
            }

            PowerUpGui.putString(new TerminalPosition(x, y + row), line.toString());
        }
    }

    @Override
    public void powerUpText(Arena arena, long timer) {

        String text = "Activated " + arena.getPowerUpCaught();
        Position position = new Position(((terminalWidth())-text.length())/2, terminalHeight() - 2);
        drawTextArena(position, text);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                arena.resetPowerUpCaught();
            }
        }, 3000); //3 secs

    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int terminalHeight() {
        return screen.getTerminalSize().getRows();
    }

    @Override
    public int terminalWidth() {
        return screen.getTerminalSize().getColumns();
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}