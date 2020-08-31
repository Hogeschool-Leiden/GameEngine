package nl.hsleiden.game.engine.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import nl.hsleiden.game.engine.abstractions.Configuration;
import nl.hsleiden.game.engine.abstractions.Game;
import nl.hsleiden.game.engine.abstractions.Graphics;
import nl.hsleiden.game.engine.abstractions.Input;
import nl.hsleiden.game.engine.dependency.injection.DependencyContainer;
import nl.hsleiden.game.engine.physics.Dimension;

import java.lang.reflect.InvocationTargetException;

public abstract class JavaFxEngine extends Application {

    public void launchEngine() {
        var configuration = getConfiguration();
        var screenSize = configuration.getScreenSize();
        var screenWidth = screenSize.getWidth();
        var screenHeight = screenSize.getHeight();
        var gameType = configuration.getGameType();
        var title = configuration.getTitle();
        var canvas = new Canvas(screenWidth, screenHeight);
        canvas.setFocusTraversable(true);
        var group = new Group(canvas);
        var stage = new Stage();
        var graphics = new JavaFxGraphics(canvas);
        var input = new JavaFxInput(canvas);


        stage.setScene(new Scene(group));
        stage.setTitle(title);

        var game = create(gameType, input, graphics, screenSize, stage);

        stage.setOnShowing((shown) -> {
            var renderer = new JavaFxRenderer(game);
            renderer.resume();
        });

        game.start();
    }

    private Game create(Class<? extends JavaFxGame> game, Input input, Graphics graphics,
                       Dimension screenSize, Stage stage) {
        try {
            return game.getDeclaredConstructor(Dimension.class, Graphics.class, Input.class, Stage.class)
                    .newInstance(screenSize, graphics, input, stage);
        } catch (NoSuchMethodException exception) {
            System.out.println(exception.getMessage());
            System.out.println(NoSuchMethodException.class.getName());
        } catch (SecurityException exception) {
            System.out.println(exception.getMessage());
            System.out.println(SecurityException.class.getName());
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
            System.out.println(IllegalAccessException.class.getName());
        } catch (InstantiationException exception) {
            System.out.println(exception.getMessage());
            System.out.println(InstantiationException.class.getName());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.out.println(IllegalArgumentException.class.getName());
        } catch (InvocationTargetException exception) {
            System.out.println(exception.getMessage());
            System.out.println(InvocationTargetException.class.getName());
        }

        throw new UnsupportedOperationException();
    }

    public abstract Configuration<? extends JavaFxGame> getConfiguration();
}
