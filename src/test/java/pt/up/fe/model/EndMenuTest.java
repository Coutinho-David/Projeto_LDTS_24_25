package pt.up.fe.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.model.menu.EndMenu;

import java.util.List;

public class EndMenuTest {

    EndMenu endMenu = new EndMenu();

    @Test
    public void getSelected() {
        Assertions.assertEquals(0, endMenu.getSelected());
    }

    @Test
    public void getOption() {
        Assertions.assertEquals("REMATCH", endMenu.getOption());
    }

    @Test
    public void next() {
        endMenu.next();
        Assertions.assertEquals(1, endMenu.getSelected());

        endMenu.next();
        endMenu.next();
        Assertions.assertEquals(0, endMenu.getSelected());
    }

    @Test
    public void previous() {
        endMenu.next();
        Assertions.assertEquals(1, endMenu.getSelected());
        endMenu.previous();
        Assertions.assertEquals(0, endMenu.getSelected());
        endMenu.previous();
        Assertions.assertEquals(2, endMenu.getSelected());
    }

    @Test
    public void getOptions() {
        List<String> options = List.of("REMATCH", "MAIN MENU", "EXIT");
        Assertions.assertEquals(options, endMenu.getOptions());
    }

    @Test
    public void getWinner() {
        Assertions.assertEquals("", endMenu.getWinner());
    }

    @Test
    public void setWinner() {
        endMenu.setWinner("player1");
        Assertions.assertEquals("player1", endMenu.getWinner());
    }

    @Test
    public void setSeriesLength() {
        endMenu.setSeriesLength(2);
        Assertions.assertEquals(2, endMenu.getSeriesLength());
    }

}