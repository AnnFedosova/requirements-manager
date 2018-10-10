package dto;

import java.io.Serializable;

public class ProjectDTO implements Serializable {

    private long id;
    private String name;
    private String description;

    public ProjectDTO(){}

    public ProjectDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setName(String projectName) {
        this.name = projectName;
    }

    @Override
    public String toString() {
        return "Project {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '}';
    }
}