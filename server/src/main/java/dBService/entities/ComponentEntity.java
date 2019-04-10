package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Component_Type")
public class ComponentEntity implements Serializable {
    private static final long serialVersionUID = 10_04_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "component_type_id",nullable = false)
    private ComponentTypeEntity componentType;

    public ComponentEntity() {
    }

    public ComponentEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ComponentEntity(ComponentEntity componentEntity){
        this.id=componentEntity.id;
        this.name=componentEntity.name;
        this.description=componentEntity.description;
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
}
