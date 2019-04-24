package servlets;

import api.UserAPI;
import dto.TestCaseDTO;
import dto.TestEnvironmentDTO;
import dto.TestPlanDTO;
import dto.TestSuiteDTO;
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

@WebServlet(name = "TestPlan", urlPatterns = "/testPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class TestPlanServlet extends HttpServlet {

    TestPlanServlet(){}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testPlanId = request.getParameter("testPlanId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testPlanId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testPlan/testPlan.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long specificationId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* когда сервер починится UserAPI.isAdmin(user.getName()) */);
        TestPlanDTO testPlan = new TestPlanDTO(1, "Test-Plan",
                "Документ, описывающий весь объем работ по тестированию. Содержит информацию по тест-кейсам, тест-наборам и пр.",
                "20-01-2019", "20-09-2019", "", 1, 1);
        pageVariables.put("testPlan", testPlan);

        List<TestCaseDTO> testCases = new ArrayList<>();
        TestCaseDTO testCase = new TestCaseDTO(
                1, 1, "Test-case 1 functional",
                "", "","","","", 1);
        testCases.add(testCase);
        pageVariables.put("testCases", testCases);

        List<TestEnvironmentDTO> testEnvironments = new ArrayList<>();
        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        testEnvironments.add(testEnvironment);
        pageVariables.put("testEnvironments", testEnvironments);

        List<TestSuiteDTO> testSuites = new ArrayList<>();
        TestSuiteDTO testSuite = new TestSuiteDTO(
                1, "Test-suite for print function",
                "Here are tests for report printing features for the card issuance system.",
                "2, 1", 1);

        testSuites.add(testSuite);
        pageVariables.put("testSuites", testSuites);
        return pageVariables;
    }
}
