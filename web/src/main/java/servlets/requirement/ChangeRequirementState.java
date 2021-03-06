package servlets.requirement;

import api.APIActions;
import api.RequirementAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.RequirementStateDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "changeRequirementState", urlPatterns = "/changeRequirementState")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))

public class ChangeRequirementState extends HttpServlet {

    public ChangeRequirementState() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("requirement/changeRequirementState.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");

        httpResponse.setContentType("text/html;charset=utf-8");
        String requirementId = httpRequest.getParameter("requirementId");
        String stateId = httpRequest.getParameter("states");

        if (stateId == null || requirementId == null) {
            httpResponse.getWriter().println("Not changed");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            RequirementDTO requirement = RequirementAPI.getRequirement(Long.parseLong(requirementId));
            requirement.setStateId(Long.parseLong(stateId));
            Response response = RequirementAPI.editRequirement(requirement);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long requirementId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //RequirementDTO requirement = RequirementAPI.getRequirement(requirementId);
        RequirementDTO requirement = new RequirementDTO(1, "Требование 1",
                "Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true);

        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", user);

        pageVariables.put("requirement", requirement);
        pageVariables.put("state", new RequirementStateDTO(1,"Новое"));
        //pageVariables.put("state", RequirementAPI.getRequirementState(requirement.getStateId()));
        List<RequirementStateDTO> states = new ArrayList<>();
        states.add(new RequirementStateDTO(1,"Низкий"));
        states.add(new RequirementStateDTO(2,"Уточнено"));
        states.add(new RequirementStateDTO(3,"Разработано"));
        states.add(new RequirementStateDTO(4,"Протестировано"));
        states.add(new RequirementStateDTO(5,"Вошло в релиз"));
        states.add(new RequirementStateDTO(6,"Отклонено"));
        states.add(new RequirementStateDTO(7,"Отожено"));
        pageVariables.put("states", states);
        //pageVariables.put("states", RequirementAPI.getRequirementStates());

        return pageVariables;
    }
}