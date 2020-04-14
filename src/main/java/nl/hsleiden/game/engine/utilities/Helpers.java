package nl.hsleiden.game.engine.utilities;

import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.physics.BoundingBox;
import nl.hsleiden.game.engine.physics.Dimension;
import nl.hsleiden.game.engine.physics.Vector;

import java.util.Collection;

public class Helpers {
    public static BoundingBox toBoundingBox(Vector location, Dimension dimension) {
        return new BoundingBox(location, dimension);
    }

    public static void drawBoundingBoxes(Collection<BoundingBox> boundingBoxes, Graphics graphics) {
        boundingBoxes.forEach(box -> graphics.drawRect(box.location, box.dimension, ARGBColor.RED));
    }
}
