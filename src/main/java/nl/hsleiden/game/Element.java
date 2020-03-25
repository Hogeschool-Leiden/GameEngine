package nl.hsleiden.game;

import javafx.scene.canvas.GraphicsContext;
import nl.hsleiden.behaviour.Behaviour;
import nl.hsleiden.property.Property;

public abstract class Element implements Property {
    private Iterable<Behaviour> behaviours;

    public abstract void draw(final GraphicsContext context);
}
