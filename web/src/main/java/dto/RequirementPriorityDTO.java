package dto;

import java.io.Serializable;

public class RequirementPriorityDTO implements Serializable {
    private long id;
    private String name;

    public RequirementPriorityDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String stateName) {
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
