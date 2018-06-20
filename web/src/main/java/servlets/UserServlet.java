package servlets;

import api.UserAPI;
import dto.UserDTO;
import templater.PageGenerator;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "User", urlPatterns = "/user")
//@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class UserServlet extends HttpServlet{

    public UserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        System.out.println("userServlet doGet");

        Map<String, Object> pageVariables = null; //переменные для странички
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("user/user.html", pageVariables));
        } catch (Exception e) {
            System.err.println("doGet err");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long userId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        UserDTO user = UserAPI.getUser(userId);

        //pageVariables.put("isAdmin", UserAPI.isAdmin(request.getUserPrincipal().getName()));
        pageVariables.put("user", user);

        return pageVariables;
    }
}