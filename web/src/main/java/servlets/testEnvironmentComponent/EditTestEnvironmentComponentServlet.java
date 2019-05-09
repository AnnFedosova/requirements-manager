package servlets.testEnvironmentComponent;

import api.APIActions;
import api.TestEnvironmentAPI;
import api.TestEnvironmentComponentAPI;
import dto.TestEnvironmentComponentDTO;
import dto.TestEnvironmentDTO;
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
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Edit_TestEnvironmentComponent", urlPatterns = "/edit_testEnvironmentComponent")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditTestEnvironmentComponentServlet extends HttpServlet {

    public EditTestEnvironmentComponentServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String testEnvironmentComponentId = request.getParameter("testEnvironmentComponentId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testEnvironmentComponentId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironmentComponent/editTestEnvironmentComponent.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testEnvironmentComponentId = httpRequest.getParameter("testEnvironmentComponentId");
        String name = httpRequest.getParameter("name");
        String description = httpRequest.getParameter("description");

        if (testEnvironmentComponentId == null ) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestEnvironmentComponentDTO testEnvironmentComponent = TestEnvironmentComponentAPI.getTestEnvironmentComponent(Long.parseLong(testEnvironmentComponentId));
            testEnvironmentComponent.setName(name);
            testEnvironmentComponent.setDescription(description);

            Response response = TestEnvironmentComponentAPI.editTestEnvironmentComponent(testEnvironmentComponent);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testEnvironmentComponentId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();

        //TestEnvironmentComponentDTO testEnvironmentComponent = TestEnvironmentComponentAPI.getTestEnvironmentComponent(testEnvironmentComponentId);
        TestEnvironmentComponentDTO testEnvironmentComponent = new TestEnvironmentComponentDTO(
                1, "Test Environment Component 1", "Компонент тестовой среды. PostgreSQL — свободная объектно-реляционная система управления базами данных.");
        pageVariables.put("testEnvironmentComponent", testEnvironmentComponent);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* сервер лежит UserAPI.isAdmin(user.getName()) */);

        return pageVariables;
    }
}