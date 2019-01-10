package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ReleaseDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ReleaseAPI {

    private final static String URL = ServerConnection.API_URL + "releases/";

    public static ReleaseDTO getRelease(long releaseId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRelease/" + releaseId);
        return new Gson().fromJson(json, new TypeToken<ReleaseDTO>(){}.getType());
    }

    public List<ReleaseDTO> getAllReleases() throws Exception {
        String json = JSONHelper.getJson(URL + "getAllReleases");
        return new Gson().fromJson(json, new TypeToken<List<ReleaseDTO>>(){}.getType());
    }

    public static Response editRelease(ReleaseDTO release) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editRelease");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(release, MediaType.APPLICATION_JSON));
    }
}
