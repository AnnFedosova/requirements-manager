package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.SpecificationDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/specification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecificationService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getSpecification/{specificationId}")
    public SpecificationDTO getSpecification(@PathParam("specificationId") long id) {
        return new SpecificationDTO(dbService.getSpecification(id));
    }
}
