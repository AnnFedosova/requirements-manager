package servlets;

import api.APIActions;
import api.RequirementAPI;
import api.UserAPI;
import dto.RequirementDTO;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "new_requirement", urlPatterns = "/new_requirement")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewRequirementServlet  extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewRequirementServlet() {
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
            response.getWriter().println(PageGenerator.getInstance().getPage("requirement/newRequirement.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String projectId = request.getParameter("projectid");
        String priorityId = request.getParameter("priorities");
        String lastVersionId = request.getParameter("requirements");
        String typeId = request.getParameter("types");
        String userId = "";
        try {
            Principal user = request.getUserPrincipal();
            String userName = user.getName();
            userId = String.valueOf((UserAPI.getUser(userName)).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        String creationDate = dateFormat.format(new Date());
        Principal user = request.getUserPrincipal();
        response.setContentType("text/html;charset=utf-8");

        if (name == null || description == null || projectId == null ) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            long creatorId = UserAPI.getUser(request.getUserPrincipal().getName()).getId();
            Response restResponse = RequirementAPI.addRequirement(
                    new RequirementDTO( "10",
                                        projectId,
                                        name,
                                        description,
                                        priorityId,
                                        typeId,
                                        "1",
                                        userId,
                                        creationDate,
                                        creationDate,
                                        userId,
                                        lastVersionId,
                                        "true"));
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
        pageVariables.put("users", UserAPI.getAllUsers());
        pageVariables.put("priorities", RequirementAPI.getRequirementPriorities());
        pageVariables.put("requirements", RequirementAPI.getRequirementsByProject(Long.parseLong(projectId)));
        pageVariables.put("types", RequirementAPI.getRequirementTypes());

        return pageVariables;
    }




}
