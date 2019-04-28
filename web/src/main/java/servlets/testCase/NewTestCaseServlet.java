package servlets.testCase;

import api.*;
import dto.TestCaseDTO;
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

@WebServlet(name = "New_testCase", urlPatterns = "/new_testCase")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewTestCaseServlet extends HttpServlet {

    NewTestCaseServlet(){}
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String requirementId = request.getParameter("requirementId");
        //todo добавить возможность передать тест-планID для привязки к тест-плану, а не требованию
        Map<String, Object> pageVariables = null;

        try {
            pageVariables = createPageVariablesMap(request);
            response.setStatus(HttpServletResponse.SC_OK);
            pageVariables.put("requirementId", requirementId);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCase/newTestCase.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

        String requirementId = httpRequest.getParameter("requirementId");
        String name = httpRequest.getParameter("name");
        String plan = httpRequest.getParameter("plan");
        String startConditions = httpRequest.getParameter("startConditions");
        String endConditions = httpRequest.getParameter("endConditions");
        String data = httpRequest.getParameter("data");

        String creationDate = dateFormat.format(new Date());
        httpResponse.setContentType("text/html;charset=utf-8");

        if (name == null || requirementId == null ) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Principal creator = httpRequest.getUserPrincipal();
            String creatorName = creator.getName();
            String creatorId = String.valueOf((UserAPI.getUser(creatorName)).getId());

            Response restResponse = TestCaseAPI.addTestCase(
                    new TestCaseDTO( "",
                            requirementId,
                            name,
                            creationDate,
                            plan,
                            startConditions,
                            endConditions,
                            data,
                            creatorId));
            APIActions.checkResponseStatus(restResponse, httpResponse);
        } catch (Exception e) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        return pageVariables;
    }
}
