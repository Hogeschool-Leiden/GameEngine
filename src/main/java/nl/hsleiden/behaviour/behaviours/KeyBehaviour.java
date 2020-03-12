package nl.hsleiden.behaviour.behaviours;

import nl.hsleiden.behaviour.Behaviour;

import java.util.Set;

/**
 * Een behavoir specifiek voor key gerelateerde events.
 * */
public interface KeyBehaviour extends Behaviour {
    /**
     * Deze methode handeld een key event af.
     * @param keyCodes de keycodes die gebruikt zijn.
     * */
    void handleKeyPresses(Set<String> keyCodes);
}
