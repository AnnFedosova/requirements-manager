package servlets.testEnvironmentComponent;

import api.APIActions;
import api.TestEnvironmentAPI;
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

@WebServlet(name = "AddTestEnvironmentComponentToTestEnvironment", urlPatterns = "/addTestEnvironmentComponentToTestEnvironment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddTestEnvironmentComponentToTestEnvironmentServlet extends HttpServlet {

    AddTestEnvironmentComponentToTestEnvironmentServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testEnvironmentId = request.getParameter("testEnvironmentId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testEnvironmentId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironmentComponent/addTestEnvironmentCompToTestEnvironment.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testEnvironmentComponentId = httpRequest.getParameter("testEnvironmentComponents");
        String testEnvironmentId = httpRequest.getParameter("testEnvironmentId");

        if (testEnvironmentId == null || testEnvironmentComponentId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestEnvironmentComponentTestEnvironmentDTO testEnvironmentComponentTestEnvironment =
                    new TestEnvironmentComponentTestEnvironmentDTO(testEnvironmentComponentId, testEnvironmentId);
            Response response = TestEnvironmentAPI.addTestEnvironmentComponentToTestEnvironment(testEnvironmentComponentTestEnvironment);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testEnvironmentId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true/*сервер не компилится UserAPI.isAdmin(user.getName()) */);


        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Тестовая среда версия 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");

        List<TestEnvironmentComponentDTO> testEnvironmentComponents = new ArrayList<>();
        TestEnvironmentComponentDTO testEnvironmentComponent = new TestEnvironmentComponentDTO(
                1, "Test Environment Component 1", "Компонент тесовой среды");
        testEnvironmentComponents.add(testEnvironmentComponent);

        pageVariables.put("testEnvironmentComponents", testEnvironmentComponents);
        pageVariables.put("testEnvironment", testEnvironment);

        return pageVariables;
    }
}

