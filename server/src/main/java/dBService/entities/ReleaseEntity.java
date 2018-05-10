package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "releases")
public class ReleaseEntity implements Serializable {
    private static final long serialVersionUID = 10052018;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discription",columnDefinition = "text")
    private String discription;

    @Column(name = "releaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @OneToMany(mappedBy = "release")
    private Set<ReleaseRequirementEntity> releases;

    public ReleaseEntity(){}

    public ReleaseEntity(String name, String discription, Date releaseDate) {
        this.name = name;
        this.discription = discription;
        this.releaseDate = releaseDate;
    }

    public ReleaseEntity(ReleaseEntity releaseEntety) {
        this.id = releaseEntety.id;
        this.name = releaseEntety.name;
        this.discription = releaseEntety.discription;
        this.releaseDate = releaseEntety.releaseDate;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}