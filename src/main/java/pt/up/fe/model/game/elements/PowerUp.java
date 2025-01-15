package pt.up.fe.model.game.elements;

import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.Position;
import pt.up.fe.model.game.arena.Arena;

public interface PowerUp {
    void activate(Arena arena, int touch);
    Position getPosition();
    void draw(LanternaGUI GUI);
}