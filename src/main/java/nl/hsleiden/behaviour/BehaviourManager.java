package nl.hsleiden.behaviour;

import nl.hsleiden.game.Element;

/**
 * Een interface die alle Behavior managers moeten implementeren.
 * Hierdoor kunnen ze geregistreerd worden bij het configureren.
 * */
public interface BehaviourManager {
    void handle(Element element);
}
