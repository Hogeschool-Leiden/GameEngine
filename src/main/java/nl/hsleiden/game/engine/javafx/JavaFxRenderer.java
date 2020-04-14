package nl.hsleiden.game.engine.javafx;

import javafx.animation.AnimationTimer;
import nl.hsleiden.game.engine.abstractions.Game;
import nl.hsleiden.game.engine.abstractions.Renderer;

public class JavaFxRenderer extends AnimationTimer implements Renderer {
    protected final Game game;

    public JavaFxRenderer(final Game game) {
        this.game = game;
    }

    @Override
    public void resume() {
        this.start();
    }

    @Override
    public void pause() {
        this.stop();
    }

    @Override
    public void handle(long now) {
        game.getCurrentScreen().update(now);
        game.getCurrentScreen().paint(now);
    }
}
