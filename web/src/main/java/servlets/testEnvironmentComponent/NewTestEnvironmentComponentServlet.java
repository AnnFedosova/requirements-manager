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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "New_testEnvironmentComponent", urlPatterns = "/new_testEnvironmentComponent")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewTestEnvironmentComponentServlet extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewTestEnvironmentComponentServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = null;
        String testEnvironmentId = request.getParameter("testEnvironmentId");

        try {

            pageVariables = createPageVariablesMap(request, Long.parseLong(testEnvironmentId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironmentComponent/newTestEnvironmentComponent.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Principal user = request.getUserPrincipal();
        response.setContentType("text/html;charset=utf-8");

        if (name == null ) {
            response.getWriter().println("Not  created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {

            Response restResponse = TestEnvironmentComponentAPI.addTestEnvironmentComponent(
                    new TestEnvironmentComponentDTO( name,
                            description));
            APIActions.checkResponseStatus(restResponse, response);
        } catch (Exception e) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testEnvironmentId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        //TestEnvironmentDTO testEnvironment = TestEnvironmentAPI.getTestEnvironment(testEnvironmentId);
        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");

        pageVariables.put("testEnvironment", testEnvironment);
        return pageVariables;
    }




}

