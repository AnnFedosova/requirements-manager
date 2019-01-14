package reportsgenerator;

import api.RequirementAPI;
import api.UserAPI;
import dto.RequirementDTO;

public class RequirementForReport {

    private long id;
    private long projectId;
    private String name;
    private String description;

    private long priorityId;
    private String priority; //new

    private long typeId;
    private String type; //new

    private long stateId;
    private String state;//new

    private long creatorId;
    private String creatorLogin; //new  при необходимости можно ниже добавить и creatorName и тд

    private String creationDate;
    private String modifiedDate;

    private long changerId;
    private String changerLogin; //new  при необходимости можно ниже добавить и changerName и тд

    private long lastVersionId;
    private String lastVersion; //new

    private String relevance;

    RequirementForReport(){}

    public RequirementForReport(RequirementDTO requirementDTO){
        this.id = requirementDTO.getId();
        this.projectId = requirementDTO.getProjectId();
        this.name = requirementDTO.getName();
        this.description = requirementDTO.getDescription();

        this.priorityId = requirementDTO.getPriorityId();
        //new
        try {
            this.priority = RequirementAPI.getRequirementPriority(requirementDTO.getPriorityId()).getName();
        } catch (Exception e) {
            this.priority = "-";
            e.printStackTrace();
        }

        this.typeId = requirementDTO.getTypeId();
        //new
        try {
            this.type = RequirementAPI.getRequirementType(requirementDTO.getTypeId()).getName();
        } catch (Exception e) {
            this.type = "-";
            e.printStackTrace();
        }

        this.stateId = requirementDTO.getStateId();
        //new
        try {
            this.state = RequirementAPI.getRequirementState(requirementDTO.getStateId()).getName();
        } catch (Exception e) {
            this.state = "-";
            e.printStackTrace();
        }

        this.creatorId = requirementDTO.getCreatorId();
        //new
        try {
            this.creatorLogin = UserAPI.getUser(requirementDTO.getCreatorId()).getLogin();
        } catch (Exception e) {
            this.creatorLogin = "-";
            e.printStackTrace();
        }

        this.creationDate = requirementDTO.getCreationDate();
        this.modifiedDate = requirementDTO.getModifiedDate();

        this.changerId = requirementDTO.getChangerId();
        //new
        try {
            this.changerLogin = UserAPI.getUser(requirementDTO.getChangerId()).getLogin();
        } catch (Exception e) {
            this.changerLogin = "-";
            e.printStackTrace();
        }

        this.lastVersionId = requirementDTO.getLastVersionId();
        //new
        try {
            this.lastVersion = RequirementAPI.getRequirement(requirementDTO.getLastVersionId()).getName();
        } catch (Exception e) {
            this.lastVersion = "-";
            e.printStackTrace();
        }

        this.relevance = String.valueOf(requirementDTO.isRelevance());
    }


    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public long getProjectId() {
        return projectId;
    }

    public String getDescription() {
        return description;
    }

    public long getPriorityId() {
        return priorityId;
    }

    public String getPriority() {
        return priority;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getType() {
        return type;
    }

    public long getStateId() {
        return stateId;
    }

    public String getState() {
        return state;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public long getChangerId() {
        return changerId;
    }

    public String getChangerLogin() {
        return changerLogin;
    }

    public long getLastVersionId() {
        return lastVersionId;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public String getRelevance() {
        return relevance;
    }
}
