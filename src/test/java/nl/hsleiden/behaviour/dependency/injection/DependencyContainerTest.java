package nl.hsleiden.behaviour.dependency.injection;

import nl.hsleiden.game.engine.dependency.injection.DependencyContainer;
import nl.hsleiden.game.engine.dependency.injection.DependencyContainerInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class DependencyContainerTest {
    private DependencyContainerInterface target;

    @BeforeEach
    public void beforeEach_DependencyContainerTest() {
        target = new DependencyContainer();
    }

    @Test
    public void test_GetDependency_WithNoConstructor_Should_Return_Instance() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Arrange
        target.addTransient(MockInterface.class, MockClass.class);

        // Act
        var result = target.getDependency(MockInterface.class);

        // Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void test_GetDependency_WithDependency_Should_Return_Instance_With_Dependency() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Arrange
        target.addTransient(MockClassWithDependencyInterface.class, MockClassWithDependency.class);
        target.addTransient(MockInterface.class, MockClass.class);

        // Act
        var result = target.getDependency(MockClassWithDependencyInterface.class);

        // Assert
        Assertions.assertNotNull(result);
    }
}
