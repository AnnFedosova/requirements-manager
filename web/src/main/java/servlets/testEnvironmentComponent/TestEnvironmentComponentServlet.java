package servlets.testEnvironmentComponent;

import dto.TestCaseDTO;
import dto.TestEnvironmentComponentDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TestEnvironmentComponent", urlPatterns = "/testEnvironmentComponent")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class TestEnvironmentComponentServlet extends HttpServlet {
    TestEnvironmentComponentServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testEnvironmentComponentId = request.getParameter("testEnvironmentComponentId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testEnvironmentComponentId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironmentComponent/testEnvironmentComponent.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

        @Override
        protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        }

        private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testEnvironmentComponentId) throws Exception {
            Map<String, Object> pageVariables = new HashMap<>();
            //Principal user = request.getUserPrincipal();
            pageVariables.put("isAdmin", true /* todo когда сервер починится UserAPI.isAdmin(user.getName()) */);

            TestEnvironmentComponentDTO testEnvironmentComponent = new TestEnvironmentComponentDTO(
                    1, "Test Environment Component 1", "Компонент тесовой среды");
            pageVariables.put("testEnvironmentComponent", testEnvironmentComponent);

            return pageVariables;
        }
    }


