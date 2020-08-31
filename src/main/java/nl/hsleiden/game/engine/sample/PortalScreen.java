package nl.hsleiden.game.engine.sample;

import javafx.scene.input.KeyCode;
import nl.hsleiden.game.engine.abstractions.Game;
import nl.hsleiden.game.engine.abstractions.Screen;
import nl.hsleiden.game.engine.physics.Dimension;
import nl.hsleiden.game.engine.physics.Vector;
import nl.hsleiden.game.engine.utilities.ARGBColor;

public class PortalScreen extends Screen {
    public static final Vector GRAVITY = new Vector(0, 0.2f);
    public static final Vector WIND = new Vector(0.05f, 0f);

    private Cube cube;
    private Floor floor;

    private boolean jumped;

    public PortalScreen(Game game) {
        super(game);

        resetCube();
        floor = new Floor(new Vector(0, game.getScreenSize().getHeight() - 300), game.getScreenSize().getWidth());
    }

    @Override
    public void update(float deltaTime) {
        if (game.getInput().isKeyPressed(KeyCode.SPACE)) {
            resetCube();
            return;
        }

        if (game.getInput().isKeyPressed(KeyCode.D)) {
            cube.applyForce(new Vector(0.5f, 0f));
        }

        if (game.getInput().isKeyPressed(KeyCode.A)) {
            cube.applyForce(new Vector(-0.5f, 0f));
        }

        if (game.getInput().isKeyPressed(KeyCode.W) && !jumped) {
            cube.applyForce(new Vector(0f, -0.5f));
            jumped = true;
        }

        if (cube.collidesWith(floor)) {
            cube.velocity = Vector.zero();
            jumped = false;
        } else {
            cube.applyForce(GRAVITY.multiply(cube.mass));
        }

        cube.update();
    }

    @Override
    public void paint(float deltaTime) {
        game.getGraphics().clearScreen(ARGBColor.WHITE);

        floor.draw(game.getGraphics());
        cube.draw(game.getGraphics());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void resetCube() {
        cube = new Cube(0.1f, new Vector(500, 0));
    }
}
