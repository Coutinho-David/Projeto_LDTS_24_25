package pt.up.fe.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.model.menu.Menu;

import java.util.List;

public class MenuTest {

    Menu menu = new Menu();

    @Test
    public void getSelected() {
        Assertions.assertEquals(0, menu.getSelected());
    }

    @Test
    public void getOption() {
        Assertions.assertEquals("1", menu.getOption());
    }

    @Test
    public void next() {
        menu.next();
        Assertions.assertEquals(1, menu.getSelected());
        menu.next();
        menu.next();
        menu.next();
        menu.next();
        menu.next();
        Assertions.assertEquals(0, menu.getSelected());
    }

    @Test
    public void previous() {
        menu.next();
        Assertions.assertEquals(1, menu.getSelected());

        menu.previous();
        Assertions.assertEquals(0, menu.getSelected());
        menu.previous();
        Assertions.assertEquals(5, menu.getSelected());
    }

    @Test
    public void getOptions() {
        List<String> options = List.of("1", "3", "5", "START", "INSTRUCTIONS", "EXIT");
        Assertions.assertEquals(options, menu.getOptions());
    }

    @Test
    public void getSeriesLength() {
        Assertions.assertEquals(1, menu.getSeriesLength());
    }

    @Test
    public void setSeriesLength() {
        menu.setSeriesLength(3);
        Assertions.assertEquals(3, menu.getSeriesLength());
    }


}
