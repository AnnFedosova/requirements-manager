package servlets.testSuite;

import api.APIActions;
import api.TestCaseAPI;
import api.TestSuiteAPI;
import api.UserAPI;
import dto.TestCaseDTO;
import dto.TestSuiteDTO;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@WebServlet(name = "New_testSuite", urlPatterns = "/new_testSuite")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewTestSuiteServlet extends HttpServlet {

    NewTestSuiteServlet(){}
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //String requirementId = request.getParameter("requirementId");
        String testPlanId = request.getParameter("testPlanId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, /* requirementId,*/ testPlanId );
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testSuite/newTestSuite.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        //создаем новый test-suite со страницы проекта
        String projectId = httpRequest.getParameter("projectId");
        //создаем новый test-suite со страницы тест-плана
        String testPlanId = httpRequest.getParameter("testPlanId");

        if(isNull(projectId) & isNull(testPlanId)){
            throw new IOException("projectId and testPlanId are null");
        }
        String name = httpRequest.getParameter("name");
        String plan = httpRequest.getParameter("plan");
        String description = httpRequest.getParameter("description");

        httpResponse.setContentType("text/html;charset=utf-8");

        if (name == null ) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //создаем новый test-Suite со страницы тест-плана
        if (projectId.equals("")) {
            try {
                Principal creator = httpRequest.getUserPrincipal();
                String creatorName = creator.getName();
                String creatorId = String.valueOf((UserAPI.getUser(creatorName)).getId());

                Response restResponse = TestSuiteAPI.createTestSuiteForTestPlan(
                        new TestSuiteDTO("",
                                name,
                                description,
                                plan,
                                creatorId), Long.parseLong(testPlanId));
                APIActions.checkResponseStatus(restResponse, httpResponse);
            } catch (Exception e) {
                httpResponse.getWriter().println("Not created");
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

        //создаем новый test-Suite со страницы проекта
        if (testPlanId.equals("")) {
            try {
                Principal creator = httpRequest.getUserPrincipal();
                String creatorName = creator.getName();
                String creatorId = String.valueOf((UserAPI.getUser(creatorName)).getId());

                Response restResponse = TestSuiteAPI.createTestSuiteForProject(
                        new TestSuiteDTO("",
                                name,
                                description,
                                plan,
                                creatorId), Long.parseLong(projectId));
                APIActions.checkResponseStatus(restResponse, httpResponse);
            } catch (Exception e) {
                httpResponse.getWriter().println("Not created");
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, //String requirementId,
                                                       String testPlanId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* пока не починится сервер UserAPI.isAdmin(user.getName())*/);
        //if (isNull(requirementId))
          //  requirementId = "1";
        if (isNull(testPlanId))
            testPlanId = "1";
        //pageVariables.put("requirementId", requirementId);
        pageVariables.put("testPlanId", testPlanId);

        return pageVariables;
    }
}
