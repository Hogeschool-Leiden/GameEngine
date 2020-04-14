package nl.hsleiden.game.engine.physics;

import nl.hsleiden.game.engine.abstractions.Element;

import java.util.Collection;

public interface Collisionable {
    Collection<BoundingBox> hitBoxes();

    default boolean collidesWith(Collisionable collisionable) {
        return hitBoxes()
                .stream()
                .anyMatch(externalBox -> collisionable
                        .hitBoxes()
                        .stream()
                        .anyMatch(externalBox::collidesWith));
    }

    default boolean collides(Element element) {
        return hitBoxes()
                .stream()
                .anyMatch(boundingBox -> boundingBox
                        .collidesWith(element.location));
    }
}
