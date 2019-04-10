package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "requirement_priorities")
public class RequirementPriorityEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "priority")
    private Set<RequirementEntity> requirements;

    public RequirementPriorityEntity(){}

    public RequirementPriorityEntity(String name) {
        this.name = name;
    }

    public RequirementPriorityEntity(RequirementPriorityEntity priorityEntity) {
        this.id=priorityEntity.id;
        this.name = priorityEntity.name;
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
}