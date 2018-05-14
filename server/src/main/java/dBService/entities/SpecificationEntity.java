package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "specification")
public class SpecificationEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "planned_date")
    @Temporal(TemporalType.DATE)
    private Date plannedDate;

    @OneToMany(mappedBy = "specification")
    private Set<SpecificationRequirementEntity> specification;

    public SpecificationEntity(){}

    public SpecificationEntity(String name, String description, Date plannedDate) {
        this.name = name;
        this.description = description;
        this.plannedDate = plannedDate;
    }

    public SpecificationEntity(SpecificationEntity specificationEntety) {
        this.id = specificationEntety.id;
        this.name = specificationEntety.name;
        this.description = specificationEntety.description;
        this.plannedDate = specificationEntety.plannedDate;
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

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }
}
