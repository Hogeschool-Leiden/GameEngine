package nl.hsleiden.game.engine.physics;

public class Vector {
    private final float x;
    private final float y;

    public Vector(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public Vector copy() {
        return new Vector(x, y);
    }

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y);
    }

    public Vector subtract(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y);
    }

    public Vector divide(float n) {
        return new Vector(this.x / n, this.y / n);
    }

    public Vector multiply(float n) {
        return new Vector(this.x * n, this.y * n);
    }

    public float magnitude() {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, x));
    }

    public Vector normalize() {
        var magnitude = magnitude();

        if (magnitude != 0) {
            return divide(magnitude);
        }

        return copy();
    }

    public Vector limit(float max) {
        var magnitude = magnitude();

        if (magnitude > max) {
            var ratio = max / magnitude;

            return multiply(ratio);
        }

        return copy();
    }

    public static Vector zero() {
        return new Vector(0, 0);
    }
}
