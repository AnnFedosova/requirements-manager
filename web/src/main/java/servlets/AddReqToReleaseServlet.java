package servlets;

import api.APIActions;
import api.ReleaseAPI;
import api.RequirementAPI;
import api.UserAPI;
import dto.ReleaseDTO;
import dto.ReleaseRequirementDTO;
import dto.RequirementDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddReqToRelease", urlPatterns = "/addReqToRelease")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddReqToReleaseServlet extends HttpServlet {
    public AddReqToReleaseServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String releaseId = request.getParameter("releaseId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(releaseId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("release/addReqToRelease.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");

        httpResponse.setContentType("text/html;charset=utf-8");

        String requirementId = httpRequest.getParameter("requirements");
        String releaseId = httpRequest.getParameter("releaseId");

        if (requirementId == null || releaseId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            ReleaseRequirementDTO releaseRequirementDTO = new ReleaseRequirementDTO(releaseId, requirementId);
            Response response = ReleaseAPI.addReqToRelease(releaseRequirementDTO);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long releaseId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));

        ReleaseDTO release = ReleaseAPI.getRelease(releaseId);
        List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(release.getProjectId());


        pageVariables.put("release", release);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }
}
