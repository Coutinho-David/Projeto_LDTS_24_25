package pt.up.fe.model;

public class Position {
    protected int x, y;

    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
}
