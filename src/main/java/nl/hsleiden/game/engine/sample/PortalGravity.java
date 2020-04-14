package nl.hsleiden.game.engine.sample;

import javafx.stage.Stage;
import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.abstractions.Input;
import nl.hsleiden.game.engine.abstractions.Screen;
import nl.hsleiden.game.engine.javafx.JavaFxGame;
import nl.hsleiden.game.engine.physics.Dimension;

public class PortalGravity extends JavaFxGame {

    public PortalGravity(Dimension screenSize, Graphics graphics, Input input, Stage stage) {
        super(screenSize, graphics, input, stage);
    }

    @Override
    public Screen getInitScreen() {
        return new PortalScreen(this);
    }
}
