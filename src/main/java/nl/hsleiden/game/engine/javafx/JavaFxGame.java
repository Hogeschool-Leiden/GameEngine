package nl.hsleiden.game.engine.javafx;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import nl.hsleiden.game.engine.abstractions.Game;
import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.abstractions.Input;
import nl.hsleiden.game.engine.abstractions.Screen;
import nl.hsleiden.game.engine.physics.Dimension;

public abstract class JavaFxGame implements Game {
    private final Dimension screenSize;
    private final Graphics graphics;
    private final Input input;
    private final Stage stage;

    private Screen screen;

    public JavaFxGame(Dimension screenSize, Graphics graphics, Input input, Stage stage) {
        this.screenSize = screenSize;
        this.graphics = graphics;
        this.input = input;
        this.stage = stage;
    }

    @Override
    public void start() {
        screen = getInitScreen();
        stage.show();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public void setScreen(Screen newScreen) {
        screen = newScreen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Dimension getScreenSize() {
        return screenSize;
    }
}
