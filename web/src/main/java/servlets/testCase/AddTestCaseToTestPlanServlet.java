package servlets.testCase;

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

@WebServlet(name = "AddTestCaseToTestPlan", urlPatterns = "/addTestCaseToTestPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddTestCaseToTestPlanServlet extends HttpServlet {

    AddTestCaseToTestPlanServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testPlanId = request.getParameter("testPlanId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testPlanId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCase/addTestCaseToTestPlan.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testCaseId = httpRequest.getParameter("testCases");
        String testPlanId = httpRequest.getParameter("testPlanId");

        if (testCaseId == null || testPlanId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestCaseTestPlanDTO testCaseTestPlanDTO = new TestCaseTestPlanDTO(testCaseId, testPlanId);
            Response response = TestPlanAPI.addTestCaseToTestPlan(testCaseTestPlanDTO);
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

        //List<TestSuiteDTO> testCases = TestSuiteAPI.getTestSuitesByProject(TestPlanAPI.getProjectByTestPlanId(testPlanId).getId());
        List<TestCaseDTO> testCases = new ArrayList<>();
        TestCaseDTO testCase1 = new TestCaseDTO(
                1, 1, "Test-case 1 functional",
                "20-01-2019", "","","","", 1);
        testCases.add(testCase1);
        TestCaseDTO testCase2 = new TestCaseDTO(
                2, 1, "Интеграционный тест-кейс 1",
                "", "","","","", 1);
        testCases.add(testCase2);

        pageVariables.put("testPlan", testPlan);
        pageVariables.put("testCases", testCases);

        return pageVariables;
    }
}

