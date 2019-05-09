package servlets.testPlan;

import api.APIActions;
import api.TestEnvironmentAPI;
import api.TestPlanAPI;
import api.UserAPI;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "New_testPlan", urlPatterns = "/new_testPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewTestPlanServlet extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewTestPlanServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = null;
        String specificationId = request.getParameter("specificationId");

        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(specificationId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testPlan/newTestPlan.html", pageVariables));

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
        String date_from = request.getParameter("date_from");
        String date_to = request.getParameter("date_to");
        String comment = request.getParameter("comment");
        String test_environment_id = request.getParameter("test_environment_id");


        Principal user = request.getUserPrincipal();
        response.setContentType("text/html;charset=utf-8");
        String creatorId;
        try {
            creatorId = String.valueOf((UserAPI.getUser(user.getName())).getId());
        } catch (Exception e) {
            creatorId = "0";
        }

        if (name == null ) {
            response.getWriter().println("Not  created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Response restResponse = TestPlanAPI.addTestPlan(
                    new TestPlanDTO( name,
                            description, date_from, date_to, comment, test_environment_id, creatorId
                    ));
            APIActions.checkResponseStatus(restResponse, response);
        } catch (Exception e) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long specificationId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);
        pageVariables.put("specificationId", specificationId);

        //pageVariables.put("testEnvironments", TestEnvironmentAPI.getAllTestEnvironments());
        List<TestEnvironmentDTO> testEnvironments = new ArrayList<>();
        TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
        testEnvironments.add(testEnvironment);
        pageVariables.put("testEnvironments", testEnvironments);


        return pageVariables;
    }




}

