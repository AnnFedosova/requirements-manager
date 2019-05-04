package servlets.testPlan;

import api.*;
import dto.RequirementDTO;
import dto.TestEnvironmentDTO;
import dto.TestPlanDTO;
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


@WebServlet(name = "Edit_TestPlan", urlPatterns = "/edit_testPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditTestPlanServlet extends HttpServlet {

    public EditTestPlanServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String testPlanId = request.getParameter("testPlanId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testPlanId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testPlan/editTestPlan.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testPlanId = httpRequest.getParameter("testPlanId");
        String name = httpRequest.getParameter("name");
        String description = httpRequest.getParameter("description");
        String date_from = httpRequest.getParameter("date_from");
        String date_to = httpRequest.getParameter("date_to");
        String comment = httpRequest.getParameter("comment");
        String testEnvironmentId = httpRequest.getParameter("testEnvironments");

        if (name == null || testEnvironmentId == null) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestPlanDTO testPlan = TestPlanAPI.getTestPlan(Long.parseLong(testPlanId));
            testPlan.setName(name);
            testPlan.setDescription(description);
            testPlan.setDate_from(date_from);
            testPlan.setDate_to(date_to);
            testPlan.setComment(comment);
            testPlan.setTest_environment_id(Long.parseLong(testEnvironmentId));

            Response response = TestPlanAPI.editTestPlan(testPlan);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testPlanId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //TestPlanDTO testPlan = TestPlanAPI.getTestPlan(testPlanId);
        TestPlanDTO testPlan = new TestPlanDTO(1, "Test-Plan",
                "Документ, описывающий весь объем работ по тестированию. Содержит информацию по тест-кейсам, тест-наборам и пр.",
                "20-01-2019", "20-09-2019", "", 1, 1);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* сервер лежит UserAPI.isAdmin(user.getName()) */);
        pageVariables.put("testPlan", testPlan);

        //pageVariables.put("testEnvironments", TestEnvironmentAPI.getAllTestEnvironments());
        List<TestEnvironmentDTO> testEnvironments = new ArrayList<>();
        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        testEnvironments.add(testEnvironment);
        pageVariables.put("testEnvironments", testEnvironments);

        return pageVariables;
    }
}