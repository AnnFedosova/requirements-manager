package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "requirement_positions")
public class RequirementPositionEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "position")
    private Set<RequirementEntity> positionSet;

    public RequirementPositionEntity(){}

    public RequirementPositionEntity(String name) {
        this.name = name;
    }

    public RequirementPositionEntity(RequirementPositionEntity requirementPositionEntity) {
        this.id = requirementPositionEntity.id;
        this.name = requirementPositionEntity.name;
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
