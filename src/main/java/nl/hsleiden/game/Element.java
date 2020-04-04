package nl.hsleiden.game;

import javafx.scene.canvas.GraphicsContext;
import nl.hsleiden.behaviour.Behaviour;
import nl.hsleiden.property.Property;

import java.util.List;

public abstract class Element implements Property {
    private List<Behaviour> behaviours;

    public void addBehaviour(final Behaviour behaviour) {
        behaviour.registerElement(this);
        this.behaviours.add(behaviour);
    }

    public void removeBehaviour(final Behaviour behaviour) {
        behaviour.unregistersElement(this);
        this.behaviours.remove(behaviour);
    }

    public abstract void draw(final GraphicsContext context);
}
