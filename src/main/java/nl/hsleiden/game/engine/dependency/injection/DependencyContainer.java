package nl.hsleiden.game.engine.dependency.injection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependencyContainer implements DependencyContainerInterface {
    private final Logger logger = Logger.getLogger(DependencyContainer.class.getName());
    private final HashMap<Class<?>, Class<?>> dependencies;

    public DependencyContainer() {
        dependencies = new HashMap<>();
    }

    public <Type, Dependency> void addTransient(Class<Type> type, Class<Dependency> dependency) {
        dependencies.put(type, dependency);
    }

    public <Type> Object getDependency(Class<Type> targetType) {
        var target = getClassOfType(targetType);
        var constructor = getConstructor(target);
        var parameterTypes = constructor.getParameterTypes();
        var parameters = getParameters(parameterTypes);
        var declaredConstructor = getDeclaredConstructor(target, parameterTypes);
        return instantiateObject(declaredConstructor, parameters);
    }

    private <Type> Class<?> getClassOfType(Class<Type> typeClass) {
        try {
            return dependencies.get(typeClass);
        } catch (NullPointerException nullPointerException) {
            var message = typeClass.getTypeName() + " is not registered in de dependency injection container.";
            logger.log(Level.WARNING, message);
            throw new DependencyInjectionException(message);
        }
    }

    private Constructor<?> getConstructor(Class<?> target) {
        try {
            var constructors = target.getConstructors();
            validateConstructor(constructors, target);
            return constructors[0];
        } catch (SecurityException securityException) {
            var message = "Access to the constructor of " + target.getTypeName() + "needs to be public.";
            throw new DependencyInjectionException(message);
        }
    }

    private void validateConstructor(Constructor<?>[] constructors, Class<?> target) {
        if (constructors.length > 1) {
            var message = target.getTypeName() + "has multiple constructors, please remove other constructors.";
            throw new DependencyInjectionException(message);
        }
    }

    private Object[] getParameters(Class<?>[] parameterTypes) {
        var parameters = new Object[parameterTypes.length];
        for (var i = 0; i < parameters.length; i++) {
            var dependency = getDependency(parameterTypes[i]);
            parameters[i] = dependency;
        }
        return parameters;
    }

    private Constructor<?> getDeclaredConstructor(Class<?> target, Class<?>[] parameterTypes) {
        try {
            return target.getDeclaredConstructor(parameterTypes);
        } catch (SecurityException securityException) {
            var message = "Access to the constructor of " + target.getTypeName() + "needs to be public.";
            throw new DependencyInjectionException(message);
        } catch (NoSuchMethodException noSuchMethodException) {
            var message = "Constructor does not exist in type" + target.getTypeName();
            throw new DependencyInjectionException(message);
        }
    }

    private Object instantiateObject(Constructor<?> declaredConstructor, Object[] parameters) {
        try {
            return declaredConstructor.newInstance(parameters);
        } catch (IllegalAccessException illegalAccessException) {
            var message = "Access to the constructor of " + declaredConstructor.getClass().getTypeName() + "needs to be public.";
            throw new DependencyInjectionException(message);
        } catch (IllegalArgumentException illegalArgumentException) {
            var message = "If this happens it means there is a leaking edge case, please report this issue on https://github.com/Hogeschool-Leiden/GameEngine";
            throw new DependencyInjectionException(message);
        } catch (InstantiationException instantiationException) {
            var message = declaredConstructor.getClass().getTypeName() + " is a abstract class please remove the abstract keyword";
            throw new DependencyInjectionException(message);
        } catch (InvocationTargetException invocationTargetException) {
            var message = "The constructor of " + declaredConstructor.getClass().getTypeName() + "throws " + invocationTargetException.getTargetException().getMessage();
            throw new DependencyInjectionException(message);
        }
    }

    private static class DependencyInjectionException extends RuntimeException {
        public DependencyInjectionException(String message) {
            super(message);
        }
    }
}
