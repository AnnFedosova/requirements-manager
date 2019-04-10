package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Subsystem")
public class SubsystemEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(mappedBy = "subsystem")
    private Set<RequirementEntity> requirements;

    public SubsystemEntity(){}

    public SubsystemEntity(String name, String description, ProjectEntity project) {
        this.name = name;
        this.description = description;
        this.project = project;
    }

    public SubsystemEntity(SubsystemEntity subsystemEntity){
        this.id=subsystemEntity.id;
        this.description=subsystemEntity.description;
        this.name=subsystemEntity.name;
        this.project=subsystemEntity.project;
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

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
