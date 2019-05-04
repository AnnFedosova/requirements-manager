package servlets.testSuite;

import api.*;
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

@WebServlet(name = "AddTestSuiteToTestPlan", urlPatterns = "/addTestSuiteToTestPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddTestSuiteToTestPlanServlet extends HttpServlet {
    public AddTestSuiteToTestPlanServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testPlanId = request.getParameter("testPlanId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testPlanId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testSuite/addTestSuiteToTestPlan.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testSuiteId = httpRequest.getParameter("testSuites");
        String testPlanId = httpRequest.getParameter("testPlanId");

        if (testSuiteId == null || testPlanId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestSuiteTestPlanDTO testSuiteTestPlanDTO = new TestSuiteTestPlanDTO(testSuiteId, testPlanId);
            Response response = TestPlanAPI.addTestSuiteToTestPlan(testSuiteTestPlanDTO);
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

        //List<TestSuiteDTO> testSuites = TestSuiteAPI.getTestSuitesByProject(TestPlanAPI.getProjectByTestPlanId(testPlanId).getId());
        List<TestSuiteDTO> testSuites = new ArrayList<>();
        TestSuiteDTO testSuite1 = new TestSuiteDTO(
                1, "Test-suite for print function",
                "Here are tests for report printing features for the card issuance system.",
                "2, 1", 1);
        testSuites.add(testSuite1);
        TestSuiteDTO testSuite2 = new TestSuiteDTO(
                1, "Test-suite 2",
                "Here are tests for report printing features for the card issuance system.",
                "2, 1, 3", 1);
        testSuites.add(testSuite2);

        pageVariables.put("testPlan", testPlan);
        pageVariables.put("testSuites", testSuites);

        return pageVariables;
    }
}

