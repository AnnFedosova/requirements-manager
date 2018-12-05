package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "releases")
public class ReleaseEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "releaseDate")
//    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @OneToMany(mappedBy = "release")
    private Set<ReleaseRequirementEntity> releases;

    public ReleaseEntity(){}

    public ReleaseEntity(String name, String description, Date releaseDate) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public ReleaseEntity(ReleaseEntity releaseEntity) {
        this.id = releaseEntity.id;
        this.name = releaseEntity.name;
        this.description = releaseEntity.description;
        this.releaseDate = releaseEntity.releaseDate;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}