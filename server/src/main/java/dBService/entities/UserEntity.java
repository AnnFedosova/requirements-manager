package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "User")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 10_04_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "system_role_id")
    private SystemRoleEntity systemRole;

    @OneToMany(mappedBy = "creator")
    private Set<TestCaseEntity> TestCaseCreators;

    @OneToMany(mappedBy = "tester")
    private Set<TestCaseResultEntity> TestCaseTesters;

    @OneToMany(mappedBy = "creator")
    private Set<RequirementEntity> requirementCreators;

    @OneToMany(mappedBy = "changer")
    private Set<RequirementEntity> RequirementChangers;

    @OneToMany(mappedBy = "user")
    private Set<UserProjectRoleEntity> projectUsers;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String middleName, String login, String password,SystemRoleEntity systemRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.systemRole=systemRole;
    }

    public UserEntity(String firstName, String lastName, String login, String password, SystemRoleEntity systemRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.systemRole=systemRole;
    }

    public UserEntity(UserEntity userEntity) {
        this.id = userEntity.id;
        this.firstName = userEntity.firstName;
        this.lastName = userEntity.lastName;
        this.middleName = userEntity.middleName;
        this.login = userEntity.login;
        this.password = userEntity.password;
        this.systemRole=userEntity.systemRole;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {return firstName; }

    public String getLastName() {return lastName; }

    public String getMiddleName() {return middleName; }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {this.firstName = firstName; }

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setMiddleName(String middleName) {this.middleName = middleName;}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SystemRoleEntity getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRoleEntity systemRole) {
        this.systemRole = systemRole;
    }
}
