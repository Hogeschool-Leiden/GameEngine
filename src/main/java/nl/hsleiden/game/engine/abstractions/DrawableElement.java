package nl.hsleiden.game.engine.abstractions;

import nl.hsleiden.game.engine.physics.Vector;

public abstract class DrawableElement extends Element implements Drawable {

    public DrawableElement(Vector location) {
        super(location);
    }

    public DrawableElement(float mass, Vector location) {
        super(mass, location);
    }
}
