package dto;

import java.util.Date;

public class SpecificationDTO {
    private long id;
    private String name;
    private String description;
    //private Date plannedDate;
    private String plannedDate;

    public SpecificationDTO(){}

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

    /*
    public Date getPlannedDate() {
        return plannedDate;
    }
    */

    public String getPlannedDate() {
        return plannedDate;
    }

    /*
    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }
    */
    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }
}
