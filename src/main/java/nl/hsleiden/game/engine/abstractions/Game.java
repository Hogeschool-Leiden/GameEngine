package nl.hsleiden.game.engine.abstractions;

import nl.hsleiden.game.engine.physics.Dimension;

public interface Game {
    void start();

    Input getInput();

    Graphics getGraphics();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    Screen getInitScreen();

    Dimension getScreenSize();
}
