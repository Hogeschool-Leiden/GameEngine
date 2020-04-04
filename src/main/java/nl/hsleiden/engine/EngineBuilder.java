package nl.hsleiden.engine;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import nl.hsleiden.renderer.GraphicsContext2DRenderer;
import nl.hsleiden.event.emitters.EventEmitter;
import nl.hsleiden.game.Game;
import nl.hsleiden.renderer.Renderer;
import nl.hsleiden.behaviour.Behaviour;

import java.util.ArrayList;
import java.util.List;

public class EngineBuilder {
    private final List<EventEmitter> eventEmitters;

    private Canvas canvas;
    private Game game;

    public EngineBuilder() {
        eventEmitters = new ArrayList<>();
    }

    public EngineBuilder withCanvas(final Canvas canvas) {
        this.canvas = canvas;

        return this;
    }

    public EngineBuilder withGame(final Game game) {
        this.game = game;

        return this;
    }

    public EngineBuilder withEventEmitter(final EventEmitter eventEmitter) {
        eventEmitters.add(eventEmitter);

        return this;
    }

    public <T extends Event> EngineBuilder addBehavior(EventType<T> eventType, Behaviour behaviour) {
        if (behaviour.canHandle(eventType)) {
            this.canvas.addEventHandler(eventType, behaviour::handle);
        }

        return this;
    }

    public Engine build() {
        final GraphicsContext context = canvas.getGraphicsContext2D();

        final Renderer renderer = new GraphicsContext2DRenderer(context, game);

        return new Engine(eventEmitters, renderer);
    }
}
