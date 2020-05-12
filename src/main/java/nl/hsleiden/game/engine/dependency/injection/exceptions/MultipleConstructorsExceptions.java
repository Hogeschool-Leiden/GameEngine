package nl.hsleiden.game.engine.dependency.injection.exceptions;

public class MultipleConstructorsExceptions extends RuntimeException {
    public MultipleConstructorsExceptions(String name) {
        super(name + "has multiple constructors, please remove one constructor.");
    }

}
