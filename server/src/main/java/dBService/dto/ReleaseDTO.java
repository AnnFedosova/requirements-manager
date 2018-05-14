package dBService.dto;

import dBService.entities.ReleaseEntity;

import java.util.Date;

public class ReleaseDTO {
    private long id;
    private String name;
    private String discription;
    private Date releaseDate;

    public ReleaseDTO(){}

    public ReleaseDTO(ReleaseEntity releaseEntity) {
        this.id = releaseEntity.getId();
        this.name = releaseEntity.getName();
        this.discription = releaseEntity.getDescription();
        this.releaseDate = releaseEntity.getReleaseDate();
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
