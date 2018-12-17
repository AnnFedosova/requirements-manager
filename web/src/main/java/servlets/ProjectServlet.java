package servlets;

import api.ProjectAPI;
import api.RequirementAPI;
import api.UserAPI;
import dto.ProjectDTO;
import dto.RequirementDTO;
import dto.SpecificationDTO;
import dto.UserDTO;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Project", urlPatterns = "/project")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class ProjectServlet extends HttpServlet {
    public ProjectServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("project/project.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long id) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        ProjectDTO project = ProjectAPI.getProject(id);
        List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(id);
        Principal user = request.getUserPrincipal();
        List<UserDTO> users = ProjectAPI.getUsersByProject(id);
        List<SpecificationDTO> specifications = ProjectAPI.getSpecByProjectId(id);
        //List<ReleaseDTO> releases = ProjectAPI.getReleasesByProjectId(id);


        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("project", project);
        pageVariables.put("users", users);
        pageVariables.put("specifications", specifications);
        pageVariables.put("requirements", requirements);
        //pageVariables.put("releases", releases);


        return pageVariables;
    }


}