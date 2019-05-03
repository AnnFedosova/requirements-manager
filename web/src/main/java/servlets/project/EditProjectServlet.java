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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Edit_project", urlPatterns = "/edit_project")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditProjectServlet extends HttpServlet {

    public EditProjectServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, id);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("project/editProject.html", pageVariables));
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
        String projectId = httpRequest.getParameter("id");


        if (name == null || description == null || projectId == null) {
            httpResponse.getWriter().println("Project not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            ProjectDTO project = ProjectAPI.getProject(Long.parseLong(projectId));
            project.setName(name);
            project.setDescription(description);
            Response response = ProjectAPI.editProject(project);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest httpRequest, String projectId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        ProjectDTO project = ProjectAPI.getProject(Long.parseLong(projectId));

        pageVariables.put("isAdmin", UserAPI.isAdmin(httpRequest.getUserPrincipal().getName()));
        pageVariables.put("project", project);
        return pageVariables;
    }
}
