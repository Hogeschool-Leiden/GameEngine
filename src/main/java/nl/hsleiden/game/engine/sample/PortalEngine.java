package nl.hsleiden.game.engine.sample;

import javafx.stage.Stage;
import nl.hsleiden.game.engine.abstractions.Configuration;
import nl.hsleiden.game.engine.javafx.JavaFxEngine;
import nl.hsleiden.game.engine.physics.Dimension;

public class PortalEngine extends JavaFxEngine {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public Configuration<PortalGravity> getConfiguration() {
        return new PortalConfiguration();
    }

    @Override
    public void start(Stage stage) {
        var engine = new PortalEngine();
        engine.launchEngine();
    }
}
