package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Component_Type")
public class ComponentTypeEntity implements Serializable {
    private static final long serialVersionUID = 10_04_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Id
    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "componentType")
    private Set<ComponentEntity> components;

    public ComponentTypeEntity(){}

    public ComponentTypeEntity(String name) {
        this.name = name;
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
