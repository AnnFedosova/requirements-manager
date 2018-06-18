package servlets;

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
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Requirement", urlPatterns = "/requirement")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class RequirementServlet extends HttpServlet {
    public RequirementServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("requirement/requirement.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long requirementId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        RequirementDTO requirement = RequirementAPI.getRequirement(requirementId);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("requirement", requirement);
        //pageVariables.put("creator", UserAPI.getUser(requestEntity.getCreatorId()));
        //pageVariables.put("customer", UserAPI.getUser(requestEntity.getCustomerId()));
        //pageVariables.put("priority", PriorityAPI.getPriority(requestEntity.getPriorityId()));
        //pageVariables.put("state", RequestAPI.getState(requestId));
        //pageVariables.put("tasks", TaskAPI.getTasksList(requestId));

        return pageVariables;
    }


}
