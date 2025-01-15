package pt.up.fe.viewer.game;

import pt.up.fe.gui.GUI;
import pt.up.fe.gui.LanternaGUI;
import pt.up.fe.model.game.arena.Arena;
import pt.up.fe.model.game.elements.Player;
import pt.up.fe.model.game.elements.PowerUp;
import pt.up.fe.viewer.Viewer;


import java.io.IOException;
import java.util.Objects;

public class ArenaViewer extends Viewer<Arena> {

    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        gui.clear();
        gui.drawArena(this.model);

        for (Player player : this.model.getPlayers()) {
            gui.drawPlayer(player);
        }

        for (PowerUp powerUp : this.model.getActivePowerUps()) {
            powerUp.draw((LanternaGUI) gui);
        }

        gui.drawBall(this.model.getBall());


        if (!Objects.equals(model.getPowerUpCaught(), "")) {
            gui.powerUpText(model,model.getPowerUpCaughtTime());
        }

        gui.refresh();
    }
}
