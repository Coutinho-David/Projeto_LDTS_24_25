package pt.up.fe.model.menu;

import java.util.List;

public class EndMenu {
    private final List<String>  options = List.of("REMATCH", "MAIN MENU", "EXIT");
    private int selected = 0;
    private String winner = "";
    private int seriesLength;

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

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    public int getSeriesLength() {
        return seriesLength;
    }

    public void setSeriesLength(int seriesLength) {
        this.seriesLength = seriesLength;
    }
}
