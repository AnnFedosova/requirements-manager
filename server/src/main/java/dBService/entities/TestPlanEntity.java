package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Test_Plan")
public class TestPlanEntity implements Serializable {
    private static final long serialVersionUID = 10_04_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "date_from")
    private String dateFrom;

    @Column(name = "date_to")
    private String dateTo;

    @Column(name = "comment",columnDefinition = "text")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "test_environment_id")
    private TestEnvironmentEntity testEnvironment;

    @Column(name = "creation_date")
    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @OneToMany(mappedBy = "testPlan")
    private Set<TestCasePlanEntity> testPlanCases;

    @OneToMany(mappedBy = "testPlan")
    private Set<TestSuitePlanEntity> testPlanSuites;

    @OneToMany(mappedBy = "testPlan")
    private Set<SpecificationEntity> testPlanSpecifications;

    public TestPlanEntity(){}

    public TestPlanEntity(String name, String description, String dateFrom, String dateTo, String comment,
                          TestEnvironmentEntity testEnvironment, String creationDate, UserEntity creator) {
        this.name = name;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.comment = comment;
        this.testEnvironment = testEnvironment;
        this.creationDate = creationDate;
        this.creator = creator;
    }

    public TestPlanEntity(TestPlanEntity testPlanEntity){
        this.id=testPlanEntity.id;
        this.name=testPlanEntity.name;
        this.description=testPlanEntity.description;
        this.comment=testPlanEntity.comment;
        this.dateFrom=testPlanEntity.dateFrom;
        this.dateTo=testPlanEntity.dateTo;
        this.creationDate=testPlanEntity.creationDate;
        this.creator=testPlanEntity.creator;
        this.testEnvironment=testPlanEntity.testEnvironment;
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TestEnvironmentEntity getTestEnvironment() {
        return testEnvironment;
    }

    public void setTestEnvironment(TestEnvironmentEntity testEnvironment) {
        this.testEnvironment = testEnvironment;
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
