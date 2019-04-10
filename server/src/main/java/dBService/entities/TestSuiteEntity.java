package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Test_Suite")
public class TestSuiteEntity implements Serializable {
    private static final long serialVersionUID = 10_04_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "creation_date")
    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @OneToMany(mappedBy = "testSuite")
    private Set<TestCaseSuiteEntity> testSuiteCases;

    @OneToMany(mappedBy = "testSuite")
    private Set<TestSuitePlanEntity> testSuitePlans;

    public TestSuiteEntity(){}

    public TestSuiteEntity(String name, String description, String creationDate, UserEntity creator) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.creator = creator;
    }

    public TestSuiteEntity(TestSuiteEntity testSuiteEntity){
        this.id=testSuiteEntity.id;
        this.creationDate=testSuiteEntity.creationDate;
        this.creator=testSuiteEntity.creator;
        this.description=testSuiteEntity.description;
        this.name=testSuiteEntity.name;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }
}
