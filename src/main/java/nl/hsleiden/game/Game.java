package nl.hsleiden.game;

import javafx.scene.canvas.GraphicsContext;

public abstract class Game {
    private final Iterable<Element> elements;

    public Game(final Iterable<Element> elements) {
        this.elements = elements;
    }

    public void draw(final GraphicsContext context) {
        elements.forEach(element -> element.draw(context));
    }
}
