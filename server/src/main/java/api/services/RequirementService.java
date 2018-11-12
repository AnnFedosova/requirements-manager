package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.*;
import dBService.entities.RequirementEntity;
import dBService.entities.RequirementPriorityEntity;
import dBService.entities.RequirementStateEntity;
import dBService.entities.RequirementTypeEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Path("/api/requirements")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class RequirementService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getPriority/{requirementId}")
    public RequirementPriorityDTO getPriority(@PathParam("requirementId") long requirementId) {
        return new RequirementPriorityDTO(dbService.getRequirement(requirementId).getPriority());
    }

    @GET
    @Path("getState/{requirementId}")
    public RequirementStateDTO getState(@PathParam("requirementId") long requirementId) {
        return new RequirementStateDTO(dbService.getRequirement(requirementId).getState());
    }

    @GET
    @Path("getType/{requirementId}")
    public RequirementTypeDTO getType(@PathParam("requirementId") long requirementId) {
        return new RequirementTypeDTO(dbService.getRequirement(requirementId).getType());
    }

    @GET
    @Path("getRequirement/{requirementId}")
    public RequirementStringDTO getRequirement(@PathParam("requirementId") long requirementId) {
        return new RequirementStringDTO(dbService.getRequirement(requirementId));
    }

    @GET
    @Path("getLastVersion/{requirementId}")
    public RequirementStringDTO getLastVersion(@PathParam("requirementId") long requirementId) {
        return new RequirementStringDTO(dbService.getRequirement(requirementId).getLastVersion());
    }

    @GET
    @Path("getRequirementsByProject/{projectId}")
    public List<RequirementStringDTO> getRequirementsByProject(@PathParam("projectId") long projectId) {
        List<RequirementStringDTO> requirementsDTO = new LinkedList<>();
        List<RequirementEntity> requirementEntities = dbService.getRequirementsByProject(projectId);
        for (RequirementEntity requirementEntity : requirementEntities) {
            requirementsDTO.add(new RequirementStringDTO(requirementEntity));
        }
        return requirementsDTO;
    }

    @GET
    @Path("getRequirementsBySpecification/{specificationId}")
    public List<RequirementStringDTO> getRequirementsBySpecification(@PathParam("specificationId") long specificationId) {
        List<RequirementStringDTO> requirementsDTO = new LinkedList<>();
        List<RequirementEntity> requirementEntities = dbService.getRequirementsBySpecification(specificationId);
        for (RequirementEntity requirementEntity : requirementEntities) {
            requirementsDTO.add(new RequirementStringDTO(requirementEntity));
        }
        return requirementsDTO;
    }

    @GET
    @Path("getRequirementPriorities")
    public List<RequirementPriorityDTO> getAllPriorities(){
        List<RequirementPriorityEntity> requirementPriorityEntities = dbService.getAllPriorities();
        List<RequirementPriorityDTO> requirementPriorityDTOS = new LinkedList<>();
        for (RequirementPriorityEntity requirementPriorityEntity : requirementPriorityEntities) {
            requirementPriorityDTOS.add(new RequirementPriorityDTO(requirementPriorityEntity));
        }
        return requirementPriorityDTOS;
    }

    @GET
    @Path("getRequirementStates")
    public List<RequirementStateDTO> getAllStates(){
        List<RequirementStateEntity> requirementPriorityEntities = dbService.getAllStates();
        List<RequirementStateDTO> requirementStateDTOS = new LinkedList<>();
        for (RequirementStateEntity requirementStateEntity : requirementPriorityEntities) {
            requirementStateDTOS.add(new RequirementStateDTO(requirementStateEntity));
        }
        return requirementStateDTOS;
    }

    @GET
    @Path("getRequirementTypes")
    public List<RequirementTypeDTO> getAllTypes(){
        List<RequirementTypeEntity> requirementTypeEntities = dbService.getAllTypes();
        List<RequirementTypeDTO> requirementTypeDTOS = new LinkedList<>();
        for (RequirementTypeEntity requirementTypeEntity : requirementTypeEntities) {
            requirementTypeDTOS.add(new RequirementTypeDTO(requirementTypeEntity));
        }
        return requirementTypeDTOS;
    }



    @GET
    @Path("getRequirementsByRelease/{releaseId}")
    public List<RequirementStringDTO> getRequirementsByRelease(@PathParam("releaseId") long releaseId) {
        List<RequirementStringDTO> requirementsDTO = new LinkedList<>();
        List<RequirementEntity> requirementEntities = dbService.getRequirementsByRelease(releaseId);
        for (RequirementEntity requirementEntity : requirementEntities) {
            requirementsDTO.add(new RequirementStringDTO(requirementEntity));
        }
        return requirementsDTO;
    }

    @GET
    @Path("getAllRequirements")
    public List<RequirementDTO> getAllRequirements(){
        List<RequirementEntity> requirementEntities = dbService.getAllRequirements();
        List<RequirementDTO> requirementsDTO = new LinkedList<>();
        for (RequirementEntity requirementEntity : requirementEntities) {
            requirementsDTO.add(new RequirementDTO(requirementEntity));
        }
        return requirementsDTO;
    }

    @GET
    @Path("getCreator/{requirementId}")
    public UserDTO getCreator(@PathParam("requirementId") long requirementId) {
        return new UserDTO(dbService.getRequirement(requirementId).getCreator());
    }


    @POST
    @Path("addRequirement")
    public Response addRequirement(RequirementDTO requirementDTO) {
        try {
            long requirementId = dbService.addRequirement(requirementDTO.getProjectId(),requirementDTO.getName(),
                    requirementDTO.getDescription(), requirementDTO.getPriorityId(),requirementDTO.getTypeId(),
                    requirementDTO.getCreatorId(), 0);
            String result = "Requirement added with id = " + requirementId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }

    @POST
    @Path("addVersion")
    public Response addVersion(RequirementDTO requirementDTO) {
        try {
            long requirementId = dbService.addRequirement(requirementDTO.getProjectId(),requirementDTO.getName(),
                    requirementDTO.getDescription(), requirementDTO.getPriorityId(),requirementDTO.getTypeId(),
                    requirementDTO.getCreatorId(), requirementDTO.getLastVersionId());
            String result = "Requirement added with id = " + requirementId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }

    @POST
    @Path("updateRequirement")
    public Response updateRequirement(RequirementDTO requirementDTO) {
        RequirementEntity requirementEntity = dbService.getRequirement(requirementDTO.getId());
        requirementEntity.setChanger(dbService.getUser(requirementDTO.getChangerId()));
        requirementEntity.setPriority(dbService.getRequirementPriority(requirementDTO.getPriorityId()));
        requirementEntity.setState(dbService.getRequirementState(requirementDTO.getStateId()));
        requirementEntity.setModifiedDate("Текущая дата");
        dbService.updateRequirement(requirementEntity);

        String result = "Request updated with id = " + requirementDTO.getId();
        return Response.ok().entity(result).build();
    }
}
