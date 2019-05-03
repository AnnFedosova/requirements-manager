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
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "New_specification", urlPatterns = "/new_specification")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewSpecificationServlet  extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewSpecificationServlet() {
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
            response.getWriter().println(PageGenerator.getInstance().getPage("specification/newSpecification.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String plannedDate = request.getParameter("plannedDate");
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
            Response restResponse = SpecificationAPI.addSpecification(
                    new SpecificationDTO( Long.parseLong("10"),
                            name,
                            description,
                            plannedDate,
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

