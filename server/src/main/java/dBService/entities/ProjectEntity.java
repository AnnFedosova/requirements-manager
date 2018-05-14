package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<UserProjectRoleEntity> projects;

    @OneToMany(mappedBy = "project")
    private Set<RequirementEntity> requirementProjects;

    public ProjectEntity(){
    }

    public ProjectEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProjectEntity(ProjectEntity projectEntity) {
        this.id=projectEntity.id;
        this.name = projectEntity.name;
        this.description = projectEntity.description;
    }

    public long getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

    public void setId(long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setDescription(String description) {this.description = description;}
}
