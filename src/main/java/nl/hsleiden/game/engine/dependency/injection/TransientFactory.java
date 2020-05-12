package nl.hsleiden.game.engine.dependency.injection;

public interface TransientFactory<T> {
    T create();
}
