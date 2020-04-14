package nl.hsleiden.game.engine.javafx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import nl.hsleiden.game.engine.abstractions.Input;

import java.util.HashSet;
import java.util.Set;

public class JavaFxInput implements Input {
    private final Set<KeyCode> pressedKeys;
    private final Node node;

    public JavaFxInput(Node node) {
        pressedKeys = new HashSet<>();
        this.node = node;
        init();
    }

    @Override
    public boolean isKeyPressed(KeyCode character) {
        return pressedKeys.contains(character);
    }

    private void init() {
        node.setOnKeyPressed(key -> pressedKeys.add(key.getCode()));
        node.setOnKeyReleased(key -> pressedKeys.remove(key.getCode()));
    }
}
