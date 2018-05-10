package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "system_roles")
public class SystemRoleEntity implements Serializable {
    private static final long serialVersionUID = 10052018;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "system_role")
    private Set<UserSystemRoleEntity> systemRoles;

    public SystemRoleEntity(){}

    public SystemRoleEntity(String name) {
        this.name = name;
    }

    public SystemRoleEntity(SystemRoleEntity systemRoleEntety) {
        this.id=systemRoleEntety.id;
        this.name = systemRoleEntety.name;
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