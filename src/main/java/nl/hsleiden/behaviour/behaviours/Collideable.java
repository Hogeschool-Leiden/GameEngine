package nl.hsleiden.behaviour.behaviours;

import nl.hsleiden.behaviour.Behaviour;

/**
 * Een behavior specifiek voor het afhandelen van collisions
 * */
public interface Collideable extends Behaviour {
    /**
     * Deze methode handeld een collision event af.
     *
     * @param other het collidable element.
     * */
    void handleCollision(Collideable other);
}
