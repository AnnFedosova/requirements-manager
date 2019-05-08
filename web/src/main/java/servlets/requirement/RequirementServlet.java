package servlets.requirement;

import api.RequirementAPI;
import api.UserAPI;
import dto.*;
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

        String requirementId = request.getParameter("requirementId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(requirementId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("requirement/requirement.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long requirementId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //RequirementDTO requirement = RequirementAPI.getRequirement(requirementId);
        RequirementDTO requirement = new RequirementDTO(1, "Требование 1",
                "Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true);


        List<TestCaseDTO> testCases = new ArrayList<>();
        TestCaseDTO testCase = new TestCaseDTO(
                1, 1, "Тест-кейс 1. Проверка заполнения полей",
                "20-01-2019", "1. Запустить приложение \n2. Проверить, заполнены ли поля на домашней странице","Пользователь зарегистрирован","Выйти из системы","Логин/пароль тестового пользователя: User/User", 1);
        testCases.add(testCase);

        List<RequirementDTO> dependedRequirements = new ArrayList<>();
        //dependedRequirements.add(RequirementAPI.getRequirement(2));
        //dependedRequirements.add(RequirementAPI.getRequirement(3));
        dependedRequirements.add(new RequirementDTO(1,"Требование 2",
                "Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        dependedRequirements.add(new RequirementDTO(1,"Требование 3",
                "Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        pageVariables.put("requirement", requirement);
        //UserDTO creator = UserAPI.getUser(requirement.getCreatorId());
        UserDTO creator = new UserDTO("Ольга", "Иванова", "", "OIvanova","87654321");
        pageVariables.put("creator", creator);

        //UserDTO changer = UserAPI.getUser(requirement.getCreatorId()); //getChangerId
        UserDTO changer = new UserDTO("Ольга", "Иванова", "", "OIvanova","87654321");

        if (changer == null){
            changer = UserAPI.getUser(requirement.getCreatorId());
        }
        pageVariables.put("changer", changer);
        //pageVariables.put("priority", RequirementAPI.getRequirementPriority(requirement.getPriorityId()));
        pageVariables.put("priority", new RequirementPriorityDTO(1, "Низкий"));

        pageVariables.put("testCases", testCases );
        pageVariables.put("dependedRequirements", dependedRequirements );

        //pageVariables.put("state", RequirementAPI.getRequirementState(requirement.getStateId()));
        pageVariables.put("state", new RequirementStateDTO(1,"Новое"));

        //pageVariables.put("type", RequirementAPI.getRequirementType(requirement.getTypeId()));
        pageVariables.put("type", new RequirementTypeDTO(1, "Функциональное требование"));

        if (requirement.getModifiedDate() == null){
            requirement.setModifiedDate(requirement.getCreationDate());
        }

        return pageVariables;
    }


}
