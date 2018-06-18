package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ProjectDTO;
import dto.SpecificationDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

public class SpecificationAPI {
    private final static String URL = ServerConnection.API_URL + "specification/";

    public static SpecificationDTO getSpecification(long specificationId) throws Exception {
        String json = JSONHelper.getJson(URL + "getSpecification/" + specificationId);
        return new Gson().fromJson(json, new TypeToken<SpecificationDTO>(){}.getType());
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
}
