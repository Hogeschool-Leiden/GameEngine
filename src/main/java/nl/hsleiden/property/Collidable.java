package nl.hsleiden.property;

public interface Collidable extends Property {

    boolean isColliding(final Iterable<Collidable> collidables);
}
