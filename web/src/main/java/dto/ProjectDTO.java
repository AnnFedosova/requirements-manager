package dto;

import java.io.Serializable;

public class ProjectDTO implements Serializable {

    private long id;
    private String name;
    private String description;

    public ProjectDTO(){}

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

    public String getTitle() {
        return name;
    }

    public void setTitle(String projectName) {
        this.name = projectName;
    }

    @Override
    public String toString() {
        return "Project {" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", description='" + description + '}';
    }
}