package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.ReleaseDTO;
import dBService.entities.ReleaseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/releases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReleaseService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getRelease/{releaseId}")
    public ReleaseDTO getRelease(@PathParam("releaseId") long id) {
        return new ReleaseDTO(dbService.getRelease(id));
    }

    @GET
    @Path("getAllReleases")
    public List<ReleaseDTO> gerAllUsers() {
        List<ReleaseEntity> releaseEntities = dbService.getAllReleases();
        List<ReleaseDTO> releaseDTOS = new LinkedList<>();
        for (ReleaseEntity releaseEntity : releaseEntities) {
            releaseDTOS.add(new ReleaseDTO(releaseEntity));
        }
        return releaseDTOS;
    }

    @POST
    @Path("addRelease")
    public Response addRelease(ReleaseDTO releaseDTO) {
        try {
            long releaseId = dbService.addRelease(releaseDTO.getName(), releaseDTO.getDescription(), releaseDTO.getReleaseDate());
            String result = "Release added with id = " + releaseId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }
}