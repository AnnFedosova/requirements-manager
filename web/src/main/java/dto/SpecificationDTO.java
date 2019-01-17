package dto;

public class SpecificationDTO {
    private long id;
    private String name;
    private String description;
    private String plannedDate;
    private long projectId;

    public SpecificationDTO(){}

    public SpecificationDTO(long id, String name, String description, String plannedDate, long projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plannedDate = plannedDate;
        this.projectId = projectId;
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

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
