package nl.hsleiden.game.engine.sample;

import javafx.stage.Stage;
import nl.hsleiden.game.engine.abstractions.Configuration;
import nl.hsleiden.game.engine.javafx.JavaFxEngine;
import nl.hsleiden.game.engine.physics.Dimension;

public class PortalEngineExtension extends JavaFxEngine {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public Configuration<PortalGravity> getConfiguration() {
        return new Configuration<>() {
            @Override
            public Dimension getScreenSize() {
                return new Dimension(1920, 1080);
            }

            @Override
            public String getTitle() {
                return "Test | Portal";
            }

            @Override
            public Class<PortalGravity> getGameType() {
                return PortalGravity.class;
            }
        };
    }

    @Override
    public void start(Stage stage) {
        new PortalEngineExtension().launchEngine();
    }
}
