package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.ReleaseDTO;

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
}
