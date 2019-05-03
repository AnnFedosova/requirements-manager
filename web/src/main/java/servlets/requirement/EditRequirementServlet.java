package servlets.requirement;

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
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Edit_Requirement", urlPatterns = "/edit_requirement")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))

public class EditRequirementServlet extends HttpServlet {

    public EditRequirementServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("requirement/editRequirement.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");

        httpResponse.setContentType("text/html;charset=utf-8");
        String requirementId = httpRequest.getParameter("id");
        String name = httpRequest.getParameter("name");
        String description = httpRequest.getParameter("description");
        String priorityId = httpRequest.getParameter("priorities");
        String stateId = httpRequest.getParameter("states");
        String typeId = httpRequest.getParameter("types");
        String lastVersionId = httpRequest.getParameter("requirements");

        if (name == null || description == null || requirementId == null) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            RequirementDTO requirement = RequirementAPI.getRequirement(Long.parseLong(requirementId));
            requirement.setName(name);
            requirement.setDescription(description);
            requirement.setPriorityId(Long.parseLong(priorityId));
            requirement.setStateId(Long.parseLong(stateId));
            requirement.setTypeId(Long.parseLong(typeId));
            requirement.setLastVersionId(Long.parseLong(lastVersionId));

            Response response = RequirementAPI.editRequirement(requirement);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long requirementId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        RequirementDTO requirement = RequirementAPI.getRequirement(requirementId);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));

        pageVariables.put("requirement", requirement);
        pageVariables.put("requirements", RequirementAPI.getRequirementsByProject(requirement.getProjectId()));

        pageVariables.put("priority", RequirementAPI.getRequirementPriority(requirement.getPriorityId()));
        pageVariables.put("priorities", RequirementAPI.getRequirementPriorities());

        pageVariables.put("state", RequirementAPI.getRequirementState(requirement.getStateId()));
        pageVariables.put("states", RequirementAPI.getRequirementStates());

        pageVariables.put("type", RequirementAPI.getRequirementType(requirement.getTypeId()));
        pageVariables.put("types", RequirementAPI.getRequirementTypes());


        return pageVariables;
    }
}