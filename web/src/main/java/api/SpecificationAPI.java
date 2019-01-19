package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.SpecificationDTO;
import dto.SpecificationRequirementDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class SpecificationAPI {
    private final static String URL = ServerConnection.API_URL + "specifications/";

    public static SpecificationDTO getSpecification(long specificationId) throws Exception {
        String json = JSONHelper.getJson(URL + "getSpecification/" + specificationId);
        return new Gson().fromJson(json, new TypeToken<SpecificationDTO>(){}.getType());
    }

    public static Response addSpecification(SpecificationDTO specification) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addSpecification");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(specification, "application/json; charset=UTF-8"));
    }

    public List<SpecificationDTO> getAllSpecifications() throws Exception {
        String json = JSONHelper.getJson(URL + "getAllSpecifications");
        return new Gson().fromJson(json, new TypeToken<List<SpecificationDTO>>(){}.getType());
    }

    public static Response editSpecification(SpecificationDTO specification) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editSpecification");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(specification, MediaType.APPLICATION_JSON));
    }

    public static Response addReqToSpec(SpecificationRequirementDTO specificationRequirementDTO) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addReqToSpec");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(specificationRequirementDTO, "application/json; charset=UTF-8"));
    }
}
