package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Test_Case")
public class TestCaseEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "plan", columnDefinition = "text")
    private String plan;

    @Column(name = "start_conditions",columnDefinition = "text")
    private String startConditions;

    @Column(name = "end_conditions",columnDefinition = "text")
    private String endConditions;

    @Column(name = "data",columnDefinition = "text")
    private String data;

    @OneToMany(mappedBy = "testCase")
    private Set<TestCaseRequirementEntity> testCaseRequirements;

    @OneToMany(mappedBy = "testCase")
    private Set<TestCaseSuiteEntity> testCaseSuites;

    @OneToMany(mappedBy = "testCase")
    private Set<TestCaseResultEntity> testCaseResults;

    @OneToMany(mappedBy = "testCase")
    private Set<TestCasePlanEntity> testCasePlans;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private RequirementEntity requirement;

    public TestCaseEntity() {
    }

    public TestCaseEntity(TestCaseEntity testCaseEntity){
        this.id=testCaseEntity.id;
        this.creationDate=testCaseEntity.creationDate;
        this.creator=testCaseEntity.creator;
        this.data=testCaseEntity.data;
        this.endConditions=testCaseEntity.endConditions;
        this.startConditions=testCaseEntity.startConditions;
        this.plan=testCaseEntity.plan;
        this.requirement=testCaseEntity.requirement;
    }

    public TestCaseEntity(String creationDate, String plan, String startConditions, String endConditions,
                          String data, UserEntity creator, RequirementEntity requirement) {
        this.creationDate = creationDate;
        this.plan = plan;
        this.startConditions = startConditions;
        this.endConditions = endConditions;
        this.data = data;
        this.creator = creator;
        this.requirement = requirement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStartConditions() {
        return startConditions;
    }

    public void setStartConditions(String startConditions) {
        this.startConditions = startConditions;
    }

    public String getEndConditions() {
        return endConditions;
    }

    public void setEndConditions(String endConditions) {
        this.endConditions = endConditions;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public RequirementEntity getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementEntity requirement) {
        this.requirement = requirement;
    }
}
