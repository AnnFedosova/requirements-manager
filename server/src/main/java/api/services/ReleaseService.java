package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.ReleaseDTO;
import dBService.dto.ReleaseRequirementDTO;
import dBService.dto.ReleaseStringDTO;
import dBService.entities.ReleaseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/releases")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class ReleaseService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getRelease/{releaseId}")
    public ReleaseStringDTO getRelease(@PathParam("releaseId") long id) {
        return new ReleaseStringDTO(dbService.getRelease(id));
    }

    @GET
    @Path("getAllReleases")
    public List<ReleaseStringDTO> getAllReleases() {
        List<ReleaseEntity> releaseEntities = dbService.getAllReleases();
        List<ReleaseStringDTO> releaseDTOS = new LinkedList<>();
        for (ReleaseEntity releaseEntity : releaseEntities) {
            releaseDTOS.add(new ReleaseStringDTO(releaseEntity));
        }
        return releaseDTOS;
    }

    @POST
    @Path("addRelease")
    public Response addRelease(ReleaseDTO releaseDTO) {
        try {
            long releaseId = dbService.addRelease(releaseDTO.getName(), releaseDTO.getDescription(), releaseDTO.getReleaseDate(), releaseDTO.getProject());
            String result = "Release added with id = " + releaseId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }

    @POST
    @Path("addRequirementToRelease")
    public Response addRequirementToRelease(ReleaseRequirementDTO releaseRequirementDTO) {
        dbService.addRequirementToRelease(releaseRequirementDTO.getReleaseId(),releaseRequirementDTO.getRequirementId());
        String result = "Requirement added";
        return Response.ok().entity(result).build();
    }
}
