package servlets;


import api.SpecificationAPI;
import api.UserAPI;
import reportsgenerator.ReportGenerator;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Print_specification", urlPatterns = "/print_specification")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class PrintSpecificationServlet extends HttpServlet{

    public PrintSpecificationServlet(){
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //при нажатии на кнопку отчетов попадаем сюда
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = null;
        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.template(SpecificationAPI.getSpecification(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pageVariables = createPageVariablesMap(request);
            response.setStatus(HttpServletResponse.SC_OK);
            //response.getWriter().println(PageGenerator.getInstance().getPage("project/newProject.html", pageVariables));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("Посылаем пустой отчет на сервер");
        //todo послать отчет
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        return pageVariables;
    }
}
