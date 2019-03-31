package servlets;

import api.RequirementAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.TestCaseDTO;
import dto.UserDTO;
import templater.PageGenerator;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<TestCaseDTO> testCases = new ArrayList<>();
        TestCaseDTO testCaseDTO = new TestCaseDTO(
                1, 1, "Test-case 1 functional",
                "", "","","","", 1);
        testCases.add(testCaseDTO);
        List<RequirementDTO> dependedRequirements = new ArrayList<>();
        dependedRequirements.add(RequirementAPI.getRequirement(2));
        dependedRequirements.add(RequirementAPI.getRequirement(3));



        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("requirement", requirement);
        pageVariables.put("creator", UserAPI.getUser(requirement.getCreatorId()));
        UserDTO changer = UserAPI.getUser(requirement.getCreatorId()); //getChangerId

        if (changer == null){
            changer = UserAPI.getUser(requirement.getCreatorId());
        }
        pageVariables.put("changer", changer);
        pageVariables.put("priority", RequirementAPI.getRequirementPriority(requirement.getPriorityId()));

        pageVariables.put("testCases", testCases );
        pageVariables.put("dependedRequirements", dependedRequirements );

        pageVariables.put("state", RequirementAPI.getRequirementState(requirement.getStateId()));
        pageVariables.put("type", RequirementAPI.getRequirementType(requirement.getTypeId()));
        if (requirement.getModifiedDate() == null){
            requirement.setModifiedDate(requirement.getCreationDate());
        }


        return pageVariables;
    }


}
