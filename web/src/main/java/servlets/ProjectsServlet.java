package servlets;

import api.ProjectAPI;
import api.UserAPI;
import dto.ProjectDTO;
import templater.PageGenerator;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "Projects", urlPatterns = "/projects")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class ProjectsServlet extends HttpServlet {

    public ProjectsServlet() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables;
        try {
            pageVariables = createPageVariablesMap(request);

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("projects/projects.html", pageVariables));
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        //пока сервер не заработает
        ProjectDTO project1 = new ProjectDTO("Проект «Газпром»",
                "В этом проекте наша компания реализует ПО для работы с ценами на топливо для компании Газпром. ПАО «Газпром» — российская (ранее советская) транснациональная энергетическая корпорация");
        ProjectDTO project2 = new ProjectDTO("Проект «Роскосмос»",
                "Реализуется ПО, которое служит для выбора кандидатов на работу в космической отрасли.");
        ProjectDTO project3 = new ProjectDTO("Проект «Сбербанк России»",
                "«Сбербанк» — российский финансовый конгломерат, крупнейший транснациональный и универсальный банк России, Центральной и Восточной Европы. Контролируется Центральным банком Российской Федерации, которому принадлежат более 52 % акций.");
        List<ProjectDTO> projects = new ArrayList<>();
        //List<ProjectDTO> projects = ProjectAPI.getProjectsList();

        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        pageVariables.put("projects", projects);
        return pageVariables;
    }
}