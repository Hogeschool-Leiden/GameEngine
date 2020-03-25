package nl.hsleiden.event.emitters;

import javafx.scene.canvas.Canvas;
import nl.hsleiden.property.Collidable;
import nl.hsleiden.events.CollisionEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CollisionChecker implements EventEmitter {
    public static final int INITIAL_DELAY = 0;
    public static final int PERIOD = 1000 / 60;

    private final Iterable<Collidable> collidables;
    private final Canvas canvas;

    public CollisionChecker(final Iterable<Collidable> collidables, final Canvas canvas) {
        this.collidables = collidables;
        this.canvas = canvas;
    }

    @Override
    public void startEmitting() {
        Executors
                .newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(this::checkForCollisions,
                        INITIAL_DELAY,
                        PERIOD,
                        TimeUnit.NANOSECONDS);
    }

    private void checkForCollisions() {
        this.collidables.forEach(collidable -> {
            if (collidable.isColliding(collidables)) {
                fireCollisionEvent();
            }
        });
    }

    private void fireCollisionEvent() {
        this.canvas.fireEvent(new CollisionEvent());
    }
}
