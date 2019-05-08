package servlets.project;

import api.ProjectAPI;
import api.RequirementAPI;
import api.TestPlanAPI;
import api.UserAPI;
import dto.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Project", urlPatterns = "/project")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class ProjectServlet extends HttpServlet {
    public ProjectServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("project/project.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long id) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //ProjectDTO project = ProjectAPI.getProject(id);
        ProjectDTO project = new ProjectDTO("Проект для Газпрома",
                "В этом проекте наша компания реализует ПО для работы с ценами на топливо для компании Газпром. ПАО «Газпром» — российская (ранее советская) транснациональная энергетическая корпорация");

        //List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(id);
        List<RequirementDTO> requirements = new ArrayList<>();

        requirements.add(new RequirementDTO(1, "Требование 1","Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 2","Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        Principal user = request.getUserPrincipal();
        //List<UserDTO> users = ProjectAPI.getUsersByProject(id);
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("Иван", "Петров", "", "IPetrov","87654321"));
        users.add(new UserDTO("Ольга", "Иванова", "", "OIvanova","87654321"));

        //List<SpecificationDTO> specifications = ProjectAPI.getSpecByProjectId(id);
        List<SpecificationDTO> specifications = new ArrayList<>();
        specifications.add(new SpecificationDTO("Спецификация требований 1","Данный документ содержит описание функциональных требований к реализации схем проверок справочных данных.", "20-01-2019"));
        specifications.add(new SpecificationDTO("Спецификация требований к ПО 2","Данный документ содержит описание функциональных требований к реализации схем проверок справочных данных.", "20-01-2019"));

        //List<ReleaseDTO> releases = ProjectAPI.getReleasesByProjectId(id);
        List<ReleaseDTO> releases = new ArrayList<>();
        releases.add(new ReleaseDTO("Релиз 1", "В данный релиз включено большенство требований соответствующей спецификации. Реализованы требования, касающиеся обновления справочной информации.", "20-01-2019"));

        List<TestPlanDTO> testPlans = new ArrayList<>(); // TestPlanAPI.getTestPlansByProjectId(id);
        //List<TestPlanDTO> testPlans = TestPlanAPI.getTestPlansByProjectId(id);

        TestPlanDTO testPlan = new TestPlanDTO(1, "Тест-план 1",
                "Документ, описывающий весь объем работ по тестированию. Содержит информацию по тест-кейсам, тест-наборам и пр.",
                "20-01-2019", "20-09-2019", "", 1, 1);
        testPlans.add(testPlan);

        pageVariables.put("isAdmin", true);//UserAPI.isAdmin(user.getName()));
        pageVariables.put("project", project);
        pageVariables.put("users", users);
        pageVariables.put("specifications", specifications);
        pageVariables.put("requirements", requirements);
        pageVariables.put("releases", releases);
        pageVariables.put("testPlans", testPlans);

        return pageVariables;
    }


}