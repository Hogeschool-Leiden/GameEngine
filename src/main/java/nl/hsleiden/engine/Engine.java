package nl.hsleiden.engine;

import nl.hsleiden.event.emitters.EventEmitter;
import nl.hsleiden.renderer.Renderer;

public class Engine {
    private final Iterable<EventEmitter> eventEmitters;
    private final Renderer renderer;

    public Engine(final Iterable<EventEmitter> eventEmitters, final Renderer renderer) {
        this.eventEmitters = eventEmitters;
        this.renderer = renderer;
    }

    public void start() {
        // TODO: Make concurrent
        eventEmitters.forEach(EventEmitter::startEmitting);
        renderer.startRendering();
    }

    public void stop() {
        // TODO: Stop emitting events and rendering
    }
}
