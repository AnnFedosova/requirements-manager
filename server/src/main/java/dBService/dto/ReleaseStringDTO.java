package dBService.dto;

import dBService.entities.ReleaseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReleaseStringDTO {
    private long id;
    private String name;
    private String description;
    private String releaseDate;

    public ReleaseStringDTO(){}

    public ReleaseStringDTO(ReleaseEntity releaseEntity) {
        SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
        this.id = releaseEntity.getId();
        this.name = releaseEntity.getName();
        this.description = releaseEntity.getDescription();
        this.releaseDate = sdfr.format(releaseEntity.getReleaseDate());
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
