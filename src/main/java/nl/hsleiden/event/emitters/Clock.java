package nl.hsleiden.event.emitters;

import javafx.scene.canvas.Canvas;
import nl.hsleiden.event.emitters.EventEmitter;
import nl.hsleiden.events.ClockEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Clock implements EventEmitter {
    public static final int INITIAL_DELAY = 0;
    public static final int PERIOD = 1000 / 60;

    private final Canvas canvas;

    public Clock(final Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void startEmitting() {
        Executors
                .newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(this::fireClockEvents,
                        INITIAL_DELAY,
                        PERIOD,
                        TimeUnit.NANOSECONDS);
    }

    private void fireClockEvents() {
        this.canvas.fireEvent(new ClockEvent());
    }
}
