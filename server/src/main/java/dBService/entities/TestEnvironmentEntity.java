package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Test_Environment")
public class TestEnvironmentEntity implements Serializable {
    private static final long serialVersionUID = 10_04_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "testEnvironment")
    private Set<TestPlanEntity> testPlans;

    @OneToMany(mappedBy = "testEnvironment")
    private Set<TestEnvironmentComponentEntity> testEnvironmentComponents;

    public TestEnvironmentEntity(){}

    public TestEnvironmentEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TestEnvironmentEntity(TestEnvironmentEntity testEnvironmentEntity){
        this.id=testEnvironmentEntity.id;
        this.description=testEnvironmentEntity.description;
        this.name=testEnvironmentEntity.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
