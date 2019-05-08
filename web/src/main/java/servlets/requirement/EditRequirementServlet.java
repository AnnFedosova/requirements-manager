package servlets.requirement;

import api.APIActions;
import api.RequirementAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.RequirementPriorityDTO;
import dto.RequirementStateDTO;
import dto.RequirementTypeDTO;
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
        //RequirementDTO requirement = RequirementAPI.getRequirement(requirementId);
        RequirementDTO requirement = new RequirementDTO(1, "Требование 1",
                "Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true);

        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        pageVariables.put("requirement", requirement);
        List<RequirementDTO> requirements = new ArrayList<>();
        requirements.add(new RequirementDTO(1, "Требование 1","Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 2","Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        //pageVariables.put("requirements", RequirementAPI.getRequirementsByProject(requirement.getProjectId()));
        pageVariables.put("requirements", requirements);


        //pageVariables.put("priority", RequirementAPI.getRequirementPriority(requirement.getPriorityId()));
        pageVariables.put("priority", new RequirementPriorityDTO(1, "Низкий"));
        List<RequirementPriorityDTO> priorities = new ArrayList<>();

        priorities.add(new RequirementPriorityDTO(1,"Низкий"));
        priorities.add(new RequirementPriorityDTO(2,"Средний"));
        priorities.add(new RequirementPriorityDTO(3,"Высокий"));
        //pageVariables.put("priorities", RequirementAPI.getRequirementPriorities());
        pageVariables.put("priorities", priorities);

        //pageVariables.put("state", RequirementAPI.getRequirementState(requirement.getStateId()));
        pageVariables.put("state", new RequirementStateDTO(1,"Новое"));
        List<RequirementStateDTO> states = new ArrayList<>();
        states.add(new RequirementStateDTO(1,"Низкий"));
        states.add(new RequirementStateDTO(2,"Уточнено"));
        states.add(new RequirementStateDTO(3,"Разработано"));
        states.add(new RequirementStateDTO(4,"Протестировано"));
        states.add(new RequirementStateDTO(5,"Вошло в релиз"));
        states.add(new RequirementStateDTO(6,"Отклонено"));
        states.add(new RequirementStateDTO(7,"Отожено"));
        //pageVariables.put("states", RequirementAPI.getRequirementStates());
        pageVariables.put("states", states);

        pageVariables.put("type", new RequirementTypeDTO(1, "Функциональное требование"));
        //pageVariables.put("type", RequirementAPI.getRequirementType(requirement.getTypeId()));

        List<RequirementTypeDTO> types = new ArrayList<>();
        types.add(new RequirementTypeDTO(1,"Бизнес-требование"));
        types.add(new RequirementTypeDTO(2,"Требование пользователей"));
        types.add(new RequirementTypeDTO(3,"Функциональное требование"));
        types.add(new RequirementTypeDTO(4,"Нефункциональное требование"));

        //pageVariables.put("types", RequirementAPI.getRequirementTypes());
        pageVariables.put("types", types);

        return pageVariables;
    }
}