package servlets.release;

import api.ReleaseAPI;
import api.RequirementAPI;
import api.UserAPI;
import dto.ReleaseDTO;
import dto.RequirementDTO;
import templater.PageGenerator;

import javax.servlet.ServletException;
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

@WebServlet(name = "Release", urlPatterns = "/release")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class ReleaseServlet extends HttpServlet {
    public ReleaseServlet() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String releaseId = request.getParameter("releaseId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(releaseId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("release/release.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long releaseId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //ReleaseDTO release = ReleaseAPI.getRelease(releaseId);
        ReleaseDTO release = new ReleaseDTO("Релиз 1", "В данный релиз включено большенство требований соответствующей спецификации. Реализованы требования, касающиеся обновления справочной информации.", "20-01-2019");
        //List<RequirementDTO> requirements = RequirementAPI.getRequirementsByRelease(releaseId);
        List<RequirementDTO> requirements = new ArrayList<>();

        requirements.add(new RequirementDTO(1, "Требование 1","Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 2","Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        pageVariables.put("release", release);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }

}
