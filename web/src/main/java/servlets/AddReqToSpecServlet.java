package servlets;

import api.RequirementAPI;
import api.SpecificationAPI;
import api.UserAPI;
import dto.RequirementDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddReqToSpec", urlPatterns = "/addReqToSpec")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddReqToSpecServlet extends HttpServlet {
    public AddReqToSpecServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String specificationId = request.getParameter("specificationId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(specificationId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("specification/addReqToSpec.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long specificationId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));

        SpecificationDTO specification = SpecificationAPI.getSpecification(specificationId);
        List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(specification.getProjectId());


        pageVariables.put("specification", specification);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }
}
