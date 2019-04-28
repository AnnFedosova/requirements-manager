package servlets.testCaseResult;

import api.*;
import dto.TestCaseDTO;
import dto.TestCaseResultDTO;
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

@WebServlet(name = "New_testCaseResult", urlPatterns = "/new_testCaseResult")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewTestCaseResultServlet extends HttpServlet {

    NewTestCaseResultServlet(){}
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testCaseId = request.getParameter("testCaseId");
        Map<String, Object> pageVariables = null;

        try {
            pageVariables = createPageVariablesMap(request);
            response.setStatus(HttpServletResponse.SC_OK);
            pageVariables.put("testCaseId", testCaseId);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCaseResult/newTestCaseResult.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        String date = httpRequest.getParameter("date");
        String description = httpRequest.getParameter("description");
        String testCaseId = httpRequest.getParameter("testCaseId");

        //String creationDate = dateFormat.format(new Date());
        httpResponse.setContentType("text/html;charset=utf-8");

        if (date == null || testCaseId == null ) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Principal tester = httpRequest.getUserPrincipal();
            String testerName = tester.getName();
            String testerId = String.valueOf((UserAPI.getUser(testerName)).getId());

            Response restResponse = TestCaseResultAPI.addTestCaseResult(
                    new TestCaseResultDTO( "",
                            date,
                            description,
                            testCaseId,
                            testerId));
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
