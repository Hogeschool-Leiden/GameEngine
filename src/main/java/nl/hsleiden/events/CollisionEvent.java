package nl.hsleiden.events;

import javafx.event.Event;
import javafx.event.EventType;

public class CollisionEvent extends Event {
    public CollisionEvent() {
        super(new EventType<CollisionEvent>(CollisionEvent.class.getName()));
    }
}
