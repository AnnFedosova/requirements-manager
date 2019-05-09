package servlets.testEnvironment;

import api.APIActions;
import api.TestEnvironmentAPI;
import api.UserAPI;
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

@WebServlet(name = "New_testEnvironment", urlPatterns = "/new_testEnvironment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewTestEnvironmentServlet  extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewTestEnvironmentServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = null;

        try {

            pageVariables = createPageVariablesMap(request);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testEnvironment/newTestEnvironment.html", pageVariables));

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

            Response restResponse = TestEnvironmentAPI.addTestEnvironment(
                    new TestEnvironmentDTO( Long.parseLong("10"),
                            name,
                            description));
            APIActions.checkResponseStatus(restResponse, response);
        } catch (Exception e) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        return pageVariables;
    }




}

