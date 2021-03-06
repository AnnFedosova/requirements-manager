package dto;

import java.io.Serializable;

public class RequirementTypeDTO implements Serializable {

    private long id;
    private String name;

    public RequirementTypeDTO(){}

    public RequirementTypeDTO(long id, String name) {
        this.id = id;
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

    public void setName(String stateName) {
        this.name = stateName;
    }

    @Override
    public String toString() {
        return "State {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
