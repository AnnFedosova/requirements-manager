package servlets.testEnvironment;

import api.APIActions;
import api.TestPlanAPI;
import dto.*;
import templater.PageGenerator;

import javax.servlet.ServletException;
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

@WebServlet(name = "AddTestEnvironmentToTestPlan", urlPatterns = "/addTestEnvironmentToTestPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddTestEnvironmentToTestPlanServlet extends HttpServlet {

    AddTestEnvironmentToTestPlanServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testPlanId = request.getParameter("testPlanId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testPlanId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironment/addTestEnvironmentToTestPlan.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testEnvironmentId = httpRequest.getParameter("testEnvironments");
        String testPlanId = httpRequest.getParameter("testPlanId");

        if (testEnvironmentId == null || testPlanId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestEnvironmentTestPlanDTO testEnvironmentTestPlan = new TestEnvironmentTestPlanDTO(testEnvironmentId, testPlanId);
            Response response = TestPlanAPI.addTestEnvironmentToTestPlan(testEnvironmentTestPlan);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testPlanId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true/*сервер не компилится UserAPI.isAdmin(user.getName()) */);

        //TestPlanDTO testPlan = TestPlanAPI.getTestPlan(testPlanId);
        TestPlanDTO testPlan = new TestPlanDTO(1, "Test-Plan",
                "Документ, описывающий весь объем работ по тестированию. Содержит информацию по тест-кейсам, тест-наборам и пр.",
                "20-01-2019", "20-09-2019", "", 1, 1);

        //List<TestEnvironmentDTO> testEnvironments = TestEnvironmentAPI.getAllTestEnvironments();
        List<TestEnvironmentDTO> testEnvironments = new ArrayList<>();
        TestEnvironmentDTO testEnvironment1 = new TestEnvironmentDTO(1, "Тестовая среда версия 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        testEnvironments.add(testEnvironment1);
        TestEnvironmentDTO testEnvironment2 = new TestEnvironmentDTO(1, "Тестовая среда 2",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        testEnvironments.add(testEnvironment2);

        pageVariables.put("testPlan", testPlan);
        pageVariables.put("testEnvironments", testEnvironments);

        return pageVariables;
    }
}

