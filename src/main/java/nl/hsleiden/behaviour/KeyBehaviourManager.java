package nl.hsleiden.behaviour;

import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.hsleiden.behaviour.behaviours.KeyBehaviour;
import nl.hsleiden.game.Element;

import java.util.HashSet;
import java.util.Set;

/**
 * Deze class is verantwoordelijk voor het afhandelen van KeyBehavoir.
 */
public class KeyBehaviourManager implements BehaviourManager {
    private Scene scene;
    private Set<String> keyCodes;

    public KeyBehaviourManager(Stage stage) {
        this.scene = stage.getScene();
        //this.controller = new Controller();
        keyCodes = new HashSet<>();
        setupKeyConfig();
    }

    private void setupKeyConfig() {
        scene.setOnKeyPressed(event -> keyCodes.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> keyCodes.remove(event.getCode().toString()));
    }


    /**
     * Geeft aan dat er een Key event afgehandel moet worden door een element met een KeyBehavior uit het spel.
     *
     * @param element met KeyBehavior.
     */
    @Override
    public void handle(Element element) {
        KeyBehaviour keyBehavior = (KeyBehaviour) element;
        keyBehavior.handleKeyPresses(keyCodes);
    }
}
