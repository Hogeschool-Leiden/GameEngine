package nl.hsleiden.game.engine.dependency.injection;

import java.lang.reflect.InvocationTargetException;

public interface DependencyContainerInterface {
    <Type, Dependency> void addTransient(Class<Type> type, Class<Dependency> dependency);
    <Type> Object getDependency(Class<Type> typeClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
