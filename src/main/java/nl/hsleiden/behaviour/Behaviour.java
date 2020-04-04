package nl.hsleiden.behaviour;

import javafx.event.Event;
import javafx.event.EventType;
import nl.hsleiden.game.Element;

public interface Behaviour {
    <T extends Event> boolean canHandle(EventType<T> eventType);

    void handle(Event event);

    void registerElement(final Element element);

    void unregistersElement(final Element element);
}
