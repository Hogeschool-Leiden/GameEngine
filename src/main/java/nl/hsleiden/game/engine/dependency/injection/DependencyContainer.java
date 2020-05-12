package nl.hsleiden.game.engine.dependency.injection;

import nl.hsleiden.game.engine.dependency.injection.exceptions.MultipleConstructorsExceptions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class DependencyContainer implements DependencyContainerInterface {
    private final HashMap<Class<?>, Class<?>> dependencies;

    public DependencyContainer() {
        dependencies = new HashMap<>();
    }

    public <Type, Dependency> void addTransient(Class<Type> type, Class<Dependency> dependency) {
        dependencies.put(type, dependency);
    }

    public <Type> Object getDependency(Class<Type> typeClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var object = dependencies.get(typeClass);

        var constructors = object.getConstructors();

        if (constructors.length > 1) {
            throw new MultipleConstructorsExceptions(object.getTypeName());
        }

        var args = constructors[0].getParameterTypes();
        var objects = new ArrayList<>();
        for (var arg : args) {
            var dependency = getDependency(arg);
            objects.add(dependency);
        }

        var constructor = object.getDeclaredConstructor(args);

        return constructor.newInstance(objects.toArray());
    }
}
