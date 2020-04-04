package nl.hsleiden.renderer;

import javafx.scene.canvas.GraphicsContext;
import nl.hsleiden.game.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GraphicsContext2DRenderer implements Renderer {
    public static final int INITIAL_DELAY = 0;
    public static final int PERIOD = 1000 / 60;

    private final GraphicsContext context;
    private final Game game;

    public GraphicsContext2DRenderer(final GraphicsContext context, final Game game) {
        this.context = context;
        this.game = game;
    }

    @Override
    public void startRendering() {
        Executors
                .newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(this::render,
                        INITIAL_DELAY,
                        PERIOD,
                        TimeUnit.NANOSECONDS);
    }

    private void render() {
        this.game.draw(context);
    }
}
