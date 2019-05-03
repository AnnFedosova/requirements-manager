package servlets.specification;

import api.APIActions;
import api.SpecificationAPI;
import api.UserAPI;
import dto.SpecificationDTO;
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

@WebServlet(name = "Edit_specification", urlPatterns = "/edit_specification")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditSpecificationServlet extends HttpServlet {

    public EditSpecificationServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, id);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("specification/editSpecification.html", pageVariables));
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
        String plannedDate = httpRequest.getParameter("plannedDate");
        String specificationId = httpRequest.getParameter("id");


        if (name == null || description == null || specificationId == null||plannedDate == null) {
            httpResponse.getWriter().println("Specification not changed");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            SpecificationDTO specification = SpecificationAPI.getSpecification(Long.parseLong(specificationId));
            specification.setName(name);
            specification.setDescription(description);
            specification.setPlannedDate(plannedDate);

            Response response = SpecificationAPI.editSpecification(specification);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest httpRequest, String specificationId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        SpecificationDTO specification = SpecificationAPI.getSpecification(Long.parseLong(specificationId));

        pageVariables.put("isAdmin", UserAPI.isAdmin(httpRequest.getUserPrincipal().getName()));
        pageVariables.put("specification", specification);
        return pageVariables;
    }
}
