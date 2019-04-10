package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "connection_type")
public class ConnectionTypeEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "connectionType")
    private Set<RequirementDependenceEntity> connectionType;

    public ConnectionTypeEntity(){}

    public ConnectionTypeEntity(ConnectionTypeEntity connectionTypeEntity) {
        this.id=connectionTypeEntity.getId();
        this.name = connectionTypeEntity.getName();
    }

    public ConnectionTypeEntity(String name){this.name=name;}

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
