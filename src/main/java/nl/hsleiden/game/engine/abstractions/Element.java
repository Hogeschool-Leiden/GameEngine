package nl.hsleiden.game.engine.abstractions;

import nl.hsleiden.game.engine.physics.Vector;

public abstract class Element {
    private static final float DEFAULT_MASS = 1;

    public final float mass;

    public Vector location;
    public Vector velocity;
    protected Vector acceleration;

    public Element(Vector location) {
        this(DEFAULT_MASS, location);
    }

    public Element(float mass, Vector location) {
        this.mass = mass;
        this.location = location;
        this.velocity = Vector.zero();
        this.acceleration = Vector.zero();
    }

    public void applyForce(Vector force) {
        var dividedForce = force.divide(mass);
        acceleration = acceleration.add(dividedForce);
    }

    public void update() {
        velocity = velocity.add(acceleration);
        location = location.add(velocity);
        acceleration = acceleration.multiply(0);
    }
}
