package nl.hsleiden.game.engine.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.abstractions.Image;
import nl.hsleiden.game.engine.physics.Dimension;
import nl.hsleiden.game.engine.physics.Vector;
import nl.hsleiden.game.engine.utilities.ARGBColor;


public class JavaFxGraphics implements Graphics {
    private final Canvas canvas;
    private final GraphicsContext context;

    public JavaFxGraphics(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    @Override
    public void clearScreen(ARGBColor color) {
        context.setFill(convert(color));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void drawLine(Vector begin, Vector end, ARGBColor color) {
        context.setFill(convert(color));
        context.strokeLine(begin.getX(), begin.getY(), end.getX(), end.getY());
    }

    @Override
    public void drawRect(Vector location, Dimension size, ARGBColor color) {
        context.setFill(convert(color));
        context.fillRect(location.getX(), location.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public void drawImage(Image image, Vector location, Vector sourceLocation, Dimension sourceSize) {
        context.drawImage(((JavaFxImage)image).convert(), sourceLocation.getX(), sourceLocation.getY(),
                sourceSize.getWidth(), sourceSize.getHeight(), location.getX(),
                location.getY(), sourceSize.getWidth(), sourceSize.getHeight());
    }

    @Override
    public void drawImage(Image image, Vector location) {
        var javaImage = ((JavaFxImage)image).convert();
        context.drawImage(javaImage, location.getX(), location.getY());
    }

    @Override
    public void drawString(String text, Vector location, ARGBColor color) {
        context.setFont(Font.font("Verdana", 30));
        context.setFill(convert(color));
        context.fillText(text, location.getX(), location.getY());
    }

    private Color convert(ARGBColor color) {
        return Color.rgb(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
