package servlets;

import api.APIActions;
import api.RequirementAPI;
import api.SpecificationAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.SpecificationDTO;
import dto.SpecificationRequirementDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddReqToSpec", urlPatterns = "/addReqToSpec")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddReqToSpecServlet extends HttpServlet {
    public AddReqToSpecServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String specificationId = request.getParameter("specificationId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(specificationId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("specification/addReqToSpec.html", pageVariables));
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
        String specificationId = httpRequest.getParameter("specificationId");

        if (requirementId == null || specificationId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            SpecificationRequirementDTO specificationRequirementDTO = new SpecificationRequirementDTO(specificationId, requirementId);
            Response response = SpecificationAPI.addReqToSpec(specificationRequirementDTO);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long specificationId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        //SpecificationDTO specification = SpecificationAPI.getSpecification(specificationId);
        SpecificationDTO specification = new SpecificationDTO("Спецификация требований 1","Данный документ содержит описание функциональных требований к реализации схем проверок справочных данных.", "20-01-2019");

        //List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(specification.getProjectId());
        List<RequirementDTO> requirements = new ArrayList<>();

        requirements.add(new RequirementDTO(1, "Требование 1","Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 2","Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        pageVariables.put("specification", specification);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }
}
