package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "requirement_types")
public class RequirementTypeEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<RequirementEntity> typeSet;

    public RequirementTypeEntity() {
    }

    public RequirementTypeEntity(String name) {
        this.name = name;
    }

    public RequirementTypeEntity(RequirementTypeEntity requirementTypeEntity) {
        this.id = requirementTypeEntity.id;
        this.name = requirementTypeEntity.name;
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