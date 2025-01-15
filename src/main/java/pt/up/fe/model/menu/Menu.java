package pt.up.fe.model.menu;

import java.util.List;

public class Menu {
    private final List<String> options = List.of("1", "3", "5", "START", "INSTRUCTIONS", "EXIT");
    private int selected = 0;
    private int seriesLength = 1;

    public int getSelected() {
        return selected;
    }

    public String getOption() {
        return options.get(selected);
    }

    public void next() {
        this.selected = getSelected() + 1;
        if (this.selected >= this.options.size()) {
            this.selected = 0;
        }
    }

    public void previous() {
        this.selected = getSelected() - 1;
        if (this.selected < 0) {
            this.selected = this.options.size() - 1;
        }
    }

    public List<String> getOptions() {
        return options;
    }

    public int getSeriesLength() {
        return seriesLength;
    }

    public void setSeriesLength(int seriesLength) {
        this.seriesLength = seriesLength;
    }

}
