package nl.hsleiden.game.engine.abstractions;

import nl.hsleiden.game.engine.physics.Dimension;

public interface Image {
    Dimension dimension();
    Graphics.ImageFormat format();
    void dispose();
}
