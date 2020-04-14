package nl.hsleiden.game.engine.physics;

public class BoundingBox {
    public final Vector location;
    public final Dimension dimension;

    public BoundingBox(final Vector location, final Dimension dimension) {
        this.location = location;
        this.dimension = dimension;
    }

    public boolean collidesWith(BoundingBox boundingBox) {
        return location.getX() < boundingBox.location.getX() + boundingBox.dimension.getWidth() &&
                location.getX() + dimension.getWidth() > boundingBox.location.getX() &&
                location.getY() < boundingBox.location.getY() + boundingBox.dimension.getHeight() &&
                location.getY() + dimension.getHeight() > boundingBox.location.getY();
    }

    public boolean collidesWith(Vector location) {
        return location.getX() > this.location.getX() &&
                location.getX() < this.location.getX() + dimension.getWidth() &&
                location.getY() > this.location.getY() &&
                location.getY() < this.location.getY() + dimension.getHeight();
    }
}
