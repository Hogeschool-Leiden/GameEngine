package nl.hsleiden.behaviour;

import javafx.event.Event;
import javafx.event.EventType;

public interface Behaviour {
    <T extends Event> boolean canHandle(EventType<T> eventType);

    void handle(Event event);
}
