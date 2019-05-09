package servlets.testSuite;

import api.APIActions;
import api.TestPlanAPI;
import api.TestSuiteAPI;
import dto.TestEnvironmentDTO;
import dto.TestPlanDTO;
import dto.TestSuiteDTO;
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


@WebServlet(name = "Edit_TestSuite", urlPatterns = "/edit_testSuite")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditTestSuiteServlet extends HttpServlet {

    public EditTestSuiteServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String testSuiteId = request.getParameter("testSuiteId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testSuiteId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testSuite/editTestSuite.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testSuiteId = httpRequest.getParameter("testSuiteId");
        String name = httpRequest.getParameter("name");
        String description = httpRequest.getParameter("description");
        String plan = httpRequest.getParameter("plan");

        if (name == null || testSuiteId == null) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestSuiteDTO testSuite = TestSuiteAPI.getTestSuite(Long.parseLong(testSuiteId));
            testSuite.setName(name);
            testSuite.setDescription(description);
            testSuite.setPlan(plan);
         
            Response response = TestSuiteAPI.editTestSuite(testSuite);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testSuiteId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //TestSuiteDTO testSuite = TestSuiteAPI.getTestSuite(testSuiteId);
        TestSuiteDTO testSuite = new TestSuiteDTO(
                1, "Набор тестов для функции генерации отчетов",
                "Совокупность (набор) тестовых вариантов, для тестирования функции генерации отчетов ",
                "2, 1", 1);
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* сервер лежит UserAPI.isAdmin(user.getName()) */);
        pageVariables.put("testSuite", testSuite);

        return pageVariables;
    }
}