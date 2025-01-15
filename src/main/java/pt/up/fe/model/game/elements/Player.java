package pt.up.fe.model.game.elements;

import pt.up.fe.model.Position;

public class Player implements Element {
    private Position position;
    private int size = 7;
    boolean isFaster = false;
    boolean isReversed = false;

    public Player(int x, int y) {
        this.position = new Position(x,y);
    }

    public void moveUp() {
        if (isFaster) {
            if (getY() > 3)
                setY(getY() - 3);
        }
        else{
            if (getY() > 2)
                setY(getY() - 2);
            else if (getY() == 2) {
                setY(getY() - 1);
            }
        }

    }
    public void moveDown(int height) {
        if (isFaster) {
            if (getY() + getSize() < height - 3)
                setY(getY() + 3);
        }
        else{
            if (getY() + getSize() < height - 2)
                setY(getY() + 2);
        }
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public void setX(int x) {
        position.setX(x);
    }

    @Override
    public void setY(int y) {
        position.setY(y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void resetSize() {
        this.size = 7;
    }

    public void setFaster(boolean f) {
        isFaster = f;
    }

    public boolean isFaster() {return isFaster;}

    public boolean isReversed() {
        return isReversed;
    }

    public void setReversed(boolean r) {
        isReversed = r;
    }
}
