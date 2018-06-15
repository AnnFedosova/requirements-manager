package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.RequirementDTO;
import dBService.dto.RequirementPriorityDTO;
import dBService.dto.RequirementStateDTO;
import dBService.dto.RequirementTypeDTO;
import dBService.entities.RequirementEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/requirements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public RequirementDTO getRequirement(@PathParam("requirementId") long requirementId) {
        return new RequirementDTO(dbService.getRequirement(requirementId));
    }

    @GET
    @Path("getLastVersion/{requirementId}")
    public RequirementDTO getLastVersion(@PathParam("requirementId") long requirementId) {
        return new RequirementDTO(dbService.getRequirement(requirementId).getLastVersion());
    }

    @GET
    @Path("getRequirementsByProject/{projectId}")
    public List<RequirementDTO> getRequestsList(@PathParam("projectId") long projectId) {
        List<RequirementDTO> requirementsDTO = new LinkedList<>();
        List<RequirementEntity> requirementEntities = dbService.getRequirementsByProject(projectId);
        for (RequirementEntity requirementEntity : requirementEntities) {
            requirementsDTO.add(new RequirementDTO(requirementEntity));
        }
        return requirementsDTO;
    }

    @GET
    @Path("getAllRequirements")
    public List<RequirementDTO> gerAllUsers(){
        List<RequirementEntity> requirementEntities = dbService.getAllRequirements();
        List<RequirementDTO> requirementsDTO = new LinkedList<>();
        for (RequirementEntity requirementEntity : requirementEntities) {
            requirementsDTO.add(new RequirementDTO(requirementEntity));
        }
        return requirementsDTO;
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

        dbService.updateRequirement(requirementEntity);

        String result = "Request updated with id = " + requirementDTO.getId();
        return Response.ok().entity(result).build();
    }
}
