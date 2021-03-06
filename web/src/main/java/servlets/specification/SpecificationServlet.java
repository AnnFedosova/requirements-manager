package servlets.specification;

import api.RequirementAPI;
import api.SpecificationAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.SpecificationDTO;
import dto.TestPlanDTO;
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

@WebServlet(name = "Specification", urlPatterns = "/specification")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class SpecificationServlet extends HttpServlet {
    public SpecificationServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("specification/specification.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long id) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //SpecificationDTO specification = SpecificationAPI.getSpecification(id);
        SpecificationDTO specification = new SpecificationDTO("Спецификация требований 1","Данный документ содержит описание функциональных требований к реализации схем проверок справочных данных.", "20-01-2019");

        //List<RequirementDTO> requirements = RequirementAPI.getRequirementsBySpecification(id);
        List<RequirementDTO> requirements = new ArrayList<>();

        requirements.add(new RequirementDTO(1, "Требование 1","Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 2","Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        TestPlanDTO testPlan = new TestPlanDTO(1, "Test-Plan",
                "Документ, описывающий весь объем работ по тестированию. Содержит информацию по тест-кейсам, тест-наборам и пр.",
                "", "", "", 1, 1);
        List<TestPlanDTO> testPlans = new ArrayList<>();
        testPlans.add(testPlan);

        //List<RequirementDTO> noTestRequirements = RequirementAPI.getRequirementsBySpecification(id);
        List<RequirementDTO> noTestRequirements = new ArrayList<>();
        noTestRequirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);
        pageVariables.put("specification", specification);
        pageVariables.put("requirements", requirements);
        pageVariables.put("noTestRequirements", noTestRequirements);
        pageVariables.put("testPlans", testPlans);

        return pageVariables;
    }

}
