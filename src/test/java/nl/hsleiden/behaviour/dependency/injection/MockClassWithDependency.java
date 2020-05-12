package nl.hsleiden.behaviour.dependency.injection;

public class MockClassWithDependency implements MockClassWithDependencyInterface {
    private final MockInterface mockDependency;

    public MockClassWithDependency(MockInterface mockDependency) {
        this.mockDependency = mockDependency;
    }

    public MockInterface getMockDependency() {
        return mockDependency;
    }
}
