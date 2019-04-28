package servlets.project;

import api.APIActions;
import api.ProjectAPI;
import api.UserAPI;
import dto.ProjectDTO;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "New_project", urlPatterns = "/new_project")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewProjectServlet extends HttpServlet {

    public NewProjectServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = null;

        try {
            pageVariables = createPageVariablesMap(request);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("project/newProject.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        String creationDate = "10-10-2018";

        response.setContentType("text/html;charset=utf-8");

        if (name == null || description == null ) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            long creatorId = UserAPI.getUser(request.getUserPrincipal().getName()).getId();
            Response restResponse = ProjectAPI.addProject(new ProjectDTO(name, description));
            APIActions.checkResponseStatus(restResponse, response);
        } catch (Exception e) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        return pageVariables;
    }

}
