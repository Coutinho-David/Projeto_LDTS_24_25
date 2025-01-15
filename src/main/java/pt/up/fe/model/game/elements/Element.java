package pt.up.fe.model.game.elements;

import pt.up.fe.model.Position;

public interface Element {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    Position getPosition();
}
