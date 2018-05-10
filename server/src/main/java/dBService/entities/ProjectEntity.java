package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectEntity implements Serializable {
    private static final long serialVersionUID = 10052018;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discription",columnDefinition = "text")
    private String discription;

    @OneToMany(mappedBy = "project")
    private Set<UserProjectRoleEntity> projects;

    @OneToMany(mappedBy = "project")
    private Set<RequirementEntity> requirementProjects;

    public ProjectEntity(){
    }

    public ProjectEntity(String name, String discription) {
        this.name = name;
        this.discription = discription;
    }

    public ProjectEntity(ProjectEntity projectEntety) {
        this.id=projectEntety.id;
        this.name = projectEntety.name;
        this.discription = projectEntety.discription;
    }

    public long getId() {return id;}

    public String getName() {return name;}

    public String getDiscription() {return discription;}

    public void setId(long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setDiscription(String discription) {this.discription = discription;}
}
