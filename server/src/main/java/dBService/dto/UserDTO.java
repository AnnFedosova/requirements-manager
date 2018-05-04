package dBService.dto;

import dBService.entities.UserEntity;

public class UserDTO {

    private long id;
    private String login;

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.login = userEntity.getLogin();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
