package servlets.release;

import api.APIActions;
import api.ReleaseAPI;
import api.UserAPI;
import dto.ReleaseDTO;
import templater.PageGenerator;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Edit_release", urlPatterns = "/edit_release")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditReleaseServlet extends HttpServlet {

    public EditReleaseServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, id);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("release/editRelease.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");

        httpResponse.setContentType("text/html;charset=utf-8");

        String name = httpRequest.getParameter("name");
        String description = httpRequest.getParameter("description");
        String releaseDate = httpRequest.getParameter("releaseDate");
        String releaseId = httpRequest.getParameter("id");


        if (name == null || description == null || releaseId == null||releaseDate == null) {
            httpResponse.getWriter().println("Release not changed");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            ReleaseDTO release = ReleaseAPI.getRelease(Long.parseLong(releaseId));
            release.setName(name);
            release.setDescription(description);
            release.setReleaseDate(releaseDate);

            Response response = ReleaseAPI.editRelease(release);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest httpRequest, String releaseId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //ReleaseDTO release = ReleaseAPI.getRelease(Long.parseLong(releaseId));
        ReleaseDTO release = new ReleaseDTO("Релиз 1", "В данный релиз включено большенство требований соответствующей спецификации. Реализованы требования, касающиеся обновления справочной информации.", "20-01-2019");

        //pageVariables.put("isAdmin", UserAPI.isAdmin(httpRequest.getUserPrincipal().getName()));
        pageVariables.put("isAdmin", true);

        pageVariables.put("release", release);
        return pageVariables;
    }
}
