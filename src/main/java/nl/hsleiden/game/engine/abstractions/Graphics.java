package nl.hsleiden.game.engine.abstractions;

import nl.hsleiden.game.engine.physics.Dimension;
import nl.hsleiden.game.engine.physics.Vector;
import nl.hsleiden.game.engine.utilities.ARGBColor;

public interface Graphics {
    public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    void clearScreen(ARGBColor color);

    void drawLine(Vector begin, Vector end, ARGBColor color);

    void drawRect(Vector location, Dimension size, ARGBColor color);

    void drawImage(Image image, Vector location, Vector sourceLocation, Dimension sourceSize);

    void drawImage(Image image, Vector location);

    void drawString(String text, Vector location, ARGBColor color);
}
