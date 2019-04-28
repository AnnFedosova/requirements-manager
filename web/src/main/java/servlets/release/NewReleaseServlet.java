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
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "New_release", urlPatterns = "/new_release")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewReleaseServlet  extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewReleaseServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String projectId = request.getParameter("projectid");
        Map<String, Object> pageVariables = null;

        try {

            pageVariables = createPageVariablesMap(request, projectId);
            response.setStatus(HttpServletResponse.SC_OK);
            pageVariables.put("projectid", projectId);
            response.getWriter().println(PageGenerator.getInstance().getPage("release/newRelease.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String releaseDate = request.getParameter("releaseDate");
        String projectId = request.getParameter("projectid");
        String userId = "";
        try {
            Principal user = request.getUserPrincipal();
            String userName = user.getName();
            userId = String.valueOf((UserAPI.getUser(userName)).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Principal user = request.getUserPrincipal();
        response.setContentType("text/html;charset=utf-8");

        if (name == null || description == null || projectId == null ) {
            response.getWriter().println("Not  created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            long creatorId = UserAPI.getUser(request.getUserPrincipal().getName()).getId();
            Response restResponse = ReleaseAPI.addRelease(
                    new ReleaseDTO( Long.parseLong("10"),
                            name,
                            description,
                            releaseDate,
                            Long.parseLong(projectId)));
            APIActions.checkResponseStatus(restResponse, response);
        } catch (Exception e) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, String projectId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));

        return pageVariables;
    }




}

