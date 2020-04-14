package nl.hsleiden.game.engine.sample;

import nl.hsleiden.game.engine.abstractions.DrawableElement;
import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.physics.BoundingBox;
import nl.hsleiden.game.engine.physics.Collisionable;
import nl.hsleiden.game.engine.physics.Dimension;
import nl.hsleiden.game.engine.physics.Vector;
import nl.hsleiden.game.engine.utilities.Helpers;

import java.util.Collection;
import java.util.Collections;

public class Floor extends DrawableElement implements Collisionable {
    private static final int HEIGHT = 20;

    private final float width;

    public Floor(Vector location, float width) {
        super(location);
        this.width = width;
    }

    @Override
    public void draw(Graphics graphics) {
        Helpers.drawBoundingBoxes(hitBoxes(), graphics);
    }

    @Override
    public Collection<BoundingBox> hitBoxes() {
        return Collections.singletonList(Helpers.toBoundingBox(location, new Dimension(width, HEIGHT)));
    }
}
