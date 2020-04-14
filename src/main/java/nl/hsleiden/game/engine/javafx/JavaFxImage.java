package nl.hsleiden.game.engine.javafx;

import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.abstractions.Image;
import nl.hsleiden.game.engine.physics.Dimension;

public class JavaFxImage implements Image {
    private final javafx.scene.image.Image image;

    public JavaFxImage(String url) {
        this.image = new javafx.scene.image.Image(url);
    }

    @Override
    public Dimension dimension() {
        return new Dimension((float) image.getHeight(), (float) image.getHeight());
    }

    @Override
    public Graphics.ImageFormat format() {
        return Graphics.ImageFormat.RGB565;
    }

    @Override
    public void dispose() {

    }

    public javafx.scene.image.Image convert() {
        return image;
    }
}
