package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 10052018;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "creator")
    private Set<RequirementEntity> creators;

    @OneToMany(mappedBy = "changer")
    private Set<RequirementEntity> changers;

    @OneToMany(mappedBy = "user")
    private Set<UserProjectRoleEntity> projectUsers;

    @OneToMany(mappedBy = "user")
    private Set<UserSystemRoleEntity> systemUsers;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String middlename, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.login = login;
        this.password = password;
    }

    public UserEntity(UserEntity userEntity) {
        this.id=userEntity.id;
        this.name = userEntity.name;
        this.surname = userEntity.surname;
        this.middlename = userEntity.middlename;
        this.login = userEntity.login;
        this.password = userEntity.password;
    }

    public long getId() {
        return id;
    }

    public String getName() {return name; }

    public String getSurname() {return surname; }

    public String getMiddlename() {return middlename; }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {this.name = name; }

    public void setSurname(String surname) {this.surname = surname;}

    public void setMiddlename(String middlename) {this.middlename = middlename;}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
