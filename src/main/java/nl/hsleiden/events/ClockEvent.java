package nl.hsleiden.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ClockEvent extends Event {

    public ClockEvent() {
        super(new EventType<ClockEvent>(ClockEvent.class.getName()));
    }
}
