package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "specification")
public class SpecificationEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "planned_date",nullable = false)
    private String plannedDate;

    @Column(name = "ready_flag")
    private boolean readyFlag;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "test_plan_id")
    private TestPlanEntity testPlan;

    @OneToMany(mappedBy = "specification")
    private Set<SpecificationRequirementEntity> specificationRequirements;

    @OneToMany(mappedBy = "specification")
    private Set<ReleaseEntity> releases;

    public SpecificationEntity(){}

    public SpecificationEntity(String name, String description, String plannedDate, boolean readyFlag,
                               ProjectEntity project, TestPlanEntity testPlan) {
        this.name = name;
        this.description = description;
        this.plannedDate = plannedDate;
        this.readyFlag = readyFlag;
        this.project = project;
        this.testPlan = testPlan;
    }

    public SpecificationEntity(SpecificationEntity specificationEntity) {
        this.id = specificationEntity.id;
        this.name = specificationEntity.name;
        this.description = specificationEntity.description;
        this.plannedDate = specificationEntity.plannedDate;
        this.project=specificationEntity.project;
        this.readyFlag=specificationEntity.readyFlag;
        this.testPlan=specificationEntity.testPlan;
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

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public boolean isReadyFlag() {
        return readyFlag;
    }

    public void setReadyFlag(boolean readyFlag) {
        this.readyFlag = readyFlag;
    }

    public TestPlanEntity getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlanEntity testPlan) {
        this.testPlan = testPlan;
    }
}
