package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Component_Type")
public class TestEnvironmentComponentEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_environment_id")
    private TestEnvironmentEntity testEnvironment;

    @Id
    @ManyToOne
    @JoinColumn(name = "component_id")
    private ComponentEntity component;

    public TestEnvironmentComponentEntity(){}

    public TestEnvironmentComponentEntity(TestEnvironmentEntity testEnvironment, ComponentEntity component) {
        this.testEnvironment = testEnvironment;
        this.component = component;
    }

    public TestEnvironmentComponentEntity(TestEnvironmentComponentEntity testEnvironmentComponentEntity){
        this.component=testEnvironmentComponentEntity.component;
        this.testEnvironment=testEnvironmentComponentEntity.testEnvironment;
    }

    public TestEnvironmentEntity getTestEnvironment() {
        return testEnvironment;
    }

    public void setTestEnvironment(TestEnvironmentEntity testEnvironment) {
        this.testEnvironment = testEnvironment;
    }

    public ComponentEntity getComponent() {
        return component;
    }

    public void setComponent(ComponentEntity component) {
        this.component = component;
    }
}
