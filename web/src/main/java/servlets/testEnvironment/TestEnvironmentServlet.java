package servlets.testEnvironment;

import dto.*;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TestEnvironment", urlPatterns = "/testEnvironment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class TestEnvironmentServlet extends HttpServlet {
    TestEnvironmentServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testEnvironmentId = request.getParameter("testEnvironmentId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testEnvironmentId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironment/testEnvironment.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testEnvironmentId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* todo когда сервер починится UserAPI.isAdmin(user.getName()) */);
        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        pageVariables.put("testEnvironment", testEnvironment);

        List<TestEnvironmentComponentDTO> testEnvironmentComponents = new ArrayList<>();
        TestEnvironmentComponentDTO testEnvironmentComponent = new TestEnvironmentComponentDTO(
                1, "Test Environment Component 1", "Компонент тесовой среды");
        testEnvironmentComponents.add(testEnvironmentComponent);
        pageVariables.put("testEnvironmentComponents", testEnvironmentComponents);

        return pageVariables;
    }

    }


