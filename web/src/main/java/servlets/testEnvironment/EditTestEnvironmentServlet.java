package servlets.testEnvironment;

import api.APIActions;
import api.TestEnvironmentAPI;
import api.TestPlanAPI;
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


@WebServlet(name = "Edit_TestEnvironment", urlPatterns = "/edit_testEnvironment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditTestEnvironmentServlet extends HttpServlet {

    public EditTestEnvironmentServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String testEnvironmentId = request.getParameter("testEnvironmentId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testEnvironmentId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironment/editTestEnvironment.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testEnvironmentId = httpRequest.getParameter("testEnvironmentId");
        String name = httpRequest.getParameter("name");
        String description = httpRequest.getParameter("description");

        if (testEnvironmentId == null ) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestEnvironmentDTO testEnvironment = TestEnvironmentAPI.getTestEnvironment(Long.parseLong(testEnvironmentId));
            testEnvironment.setName(name);
            testEnvironment.setDescription(description);

            Response response = TestEnvironmentAPI.editTestEnvironment(testEnvironment);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testEnvironmentId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //TestEnvironmentDTO testEnvironment = TestEnvironmentAPI.getTestEnvironment(testEnvironmentId);
        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        pageVariables.put("testEnvironment", testEnvironment);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* сервер лежит UserAPI.isAdmin(user.getName()) */);

        return pageVariables;
    }
}