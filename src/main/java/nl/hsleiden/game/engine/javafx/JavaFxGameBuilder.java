package nl.hsleiden.game.engine.javafx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import nl.hsleiden.game.engine.physics.Dimension;

public class JavaFxGameBuilder {
    private Stage stage;
    private Canvas canvas;
    private JavaFxGame game;
    private Dimension screenSize;

    public JavaFxGameBuilder(Stage stage) {
        this.stage = stage;
    }

    public JavaFxGameBuilder withTitle(String title) {
        stage.setTitle(title);
        return this;
    }

    public JavaFxGameBuilder withScreenSize(Dimension dimension) {
        canvas = new Canvas();
        canvas.setWidth(dimension.getWidth());
        canvas.setHeight(dimension.getHeight());
        canvas.setFocusTraversable(true);
        screenSize = dimension;
        return this;
    }

    public JavaFxGameBuilder withFullScreen() {
        stage.setFullScreen(true);
        return this;
    }

    public JavaFxGameBuilder withGame(JavaFxGame newGame) {
        game = newGame;
        return this;
    }

    JavaFxGameBuilder withRenderer() {
        var renderer = new JavaFxRenderer(game);
        stage.setOnShowing(shown -> renderer.resume());
        stage.setOnHidden(hidden -> renderer.pause());
        return this;
    }

    JavaFxGameBuilder withInput() {
        return this;
    }

    JavaFxGameBuilder withGraphics() {
        return this;
    }

    Stage build() {
        stage.setScene(new Scene(new Group(canvas)));
        return stage;
    }
}
