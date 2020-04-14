package nl.hsleiden.game.engine.sample;

import nl.hsleiden.game.engine.abstractions.DrawableElement;
import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.abstractions.Image;
import nl.hsleiden.game.engine.javafx.JavaFxImage;
import nl.hsleiden.game.engine.physics.BoundingBox;
import nl.hsleiden.game.engine.physics.Collisionable;
import nl.hsleiden.game.engine.physics.Dimension;
import nl.hsleiden.game.engine.physics.Vector;
import nl.hsleiden.game.engine.utilities.ARGBColor;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

public class Cube extends DrawableElement implements Collisionable {
    private final JavaFxImage image;

    public Cube(final float mass, final Vector location) {
        super(mass, location);
        this.image = new JavaFxImage(new File("cube.png").toURI().toString());
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image, location);
    }

    @Override
    public Collection<BoundingBox> hitBoxes() {
        return Collections.singleton(new BoundingBox(location, image.dimension()));
    }
}
