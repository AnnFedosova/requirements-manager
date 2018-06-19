package servlets;

import api.SpecificationAPI;
import api.UserAPI;
import dto.SpecificationDTO;
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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Specification", urlPatterns = "/specification")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class SpecificationServlet extends HttpServlet {
    public SpecificationServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //String id = request.getParameter("id");
        String id = "1";

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("specification/specification.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long id) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        SpecificationDTO specification = SpecificationAPI.getSpecification(id);

        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
        dateString = sdfr.format( specification.getPlannedDate() );
        String ttttt = null;
        ttttt = "ff";

        //TODO тут исправить дату, она приходит стремной строкой непонятной
        //List<UserProjectRoleDTO> roles = ProjectAPI.getProjectRoles(id);
        //List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(id);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("specification", specification);
        //pageVariables.put("roles", roles);
        //pageVariables.put("requirements", requirements);

        return pageVariables;
    }

}
