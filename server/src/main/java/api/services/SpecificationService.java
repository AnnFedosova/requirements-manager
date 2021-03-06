package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.SpecificationDTO;
import dBService.dto.SpecificationRequirementDTO;
import dBService.dto.SpecificationStringDTO;
import dBService.entities.SpecificationEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/specifications")

@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
/*
было
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
*/
public class SpecificationService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getSpecification/{specificationId}")
    public SpecificationDTO getSpecification(@PathParam("specificationId") long id) {
        return new SpecificationDTO(dbService.getSpecification(id));
    }

    @GET
    @Path("getAllSpecifications")
    public List<SpecificationStringDTO> getAllSpecifications() {
        List<SpecificationEntity> releaseEntities = dbService.getAllSpecifications();
        List<SpecificationStringDTO> specificationDTOS = new LinkedList<>();
        for (SpecificationEntity specificationEntity : releaseEntities) {
            specificationDTOS.add(new SpecificationStringDTO(new SpecificationDTO(specificationEntity)));
        }
        return specificationDTOS;
    }

    @POST
    @Path("addSpecification")
    public Response addUSpecification(SpecificationDTO specificationDTO) {
        try {
            long specificationId = dbService.addSpecification(specificationDTO.getName(), specificationDTO.getDescription(), specificationDTO.getPlannedDate(), specificationDTO.getProjectId());
            String result = "Specification added with id = " + specificationId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }

    @POST
    @Path("addReqToSpec")
    public Response addRequirementToSpecification(SpecificationRequirementDTO specificationRequirementDTO) {
            dbService.addRequirementToSpecification(specificationRequirementDTO.getSpecificationId(),specificationRequirementDTO.getRequirementId());
            String result = "Requirement added";
            return Response.ok().entity(result).build();
    }
}
