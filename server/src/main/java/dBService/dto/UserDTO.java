package dBService.dto;

import dBService.entities.UserEntity;

public class UserDTO {

    private long id;
    private String name;
    private String surname;
    private String middlename;
    private String login;
    private String password;

    public UserDTO(){}

    public UserDTO(String name, String surname, String middlename, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.login = login;
        this.password = password;
    }

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.surname = userEntity.getSurname();
        this.middlename = userEntity.getMiddlename();
        this.login = userEntity.getLogin();
        this.password = userEntity.getPassword();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
