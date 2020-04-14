package nl.hsleiden.game.engine.abstractions;

import nl.hsleiden.game.engine.physics.Dimension;

public interface Configuration<T extends Game> {
    Dimension getScreenSize();

    String getTitle();

    Class<T> getGameType();
}
