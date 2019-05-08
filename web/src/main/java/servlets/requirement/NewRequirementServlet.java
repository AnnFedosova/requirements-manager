package servlets.requirement;

import api.APIActions;
import api.RequirementAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.RequirementPriorityDTO;
import dto.RequirementTypeDTO;
import dto.UserDTO;
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
import java.util.*;

import static java.util.Objects.isNull;

@WebServlet(name = "new_requirement", urlPatterns = "/new_requirement")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class NewRequirementServlet  extends HttpServlet {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public NewRequirementServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String projectId = request.getParameter("projectId");
        if (isNull(projectId))
            projectId = "0";
        String specificationId = request.getParameter("specificationId");
        Map<String, Object> pageVariables = null;
        try {

            pageVariables = createPageVariablesMap(request, projectId);
            response.setStatus(HttpServletResponse.SC_OK);
            pageVariables.put("projectid", projectId);
            response.getWriter().println(PageGenerator.getInstance().getPage("requirement/newRequirement.html", pageVariables));

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
        String projectId = request.getParameter("projectid");
        String priorityId = request.getParameter("priorities");
        String lastVersionId = request.getParameter("requirements");
        String typeId = request.getParameter("types");

        String creationDate = dateFormat.format(new Date());
        response.setContentType("text/html;charset=utf-8");

        if (name == null || description == null || projectId == null ) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Principal creator = request.getUserPrincipal();
            String creatorName = creator.getName();
            String creatorId = String.valueOf((UserAPI.getUser(creatorName)).getId());
            Response restResponse = RequirementAPI.addRequirement(
                    new RequirementDTO( "10",
                                        projectId,
                                        name,
                                        description,
                                        priorityId,
                                        typeId,
                                        "1",
                                        creatorId,
                                        creationDate,
                                        creationDate,
                                        creatorId,
                                        lastVersionId,
                                        "true"));
            APIActions.checkResponseStatus(restResponse, response);
        } catch (Exception e) {
            response.getWriter().println("Not created");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, String projectId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        //pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("isAdmin", true);

        //pageVariables.put("users", UserAPI.getAllUsers());
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("Иван", "Петров", "", "IPetrov","87654321"));
        users.add(new UserDTO("Ольга", "Иванова", "", "OIvanova","87654321"));

        //pageVariables.put("priorities", RequirementAPI.getRequirementPriorities());
        List<RequirementPriorityDTO> priorities = new ArrayList<>();
        priorities.add(new RequirementPriorityDTO(1,"Низкий"));
        priorities.add(new RequirementPriorityDTO(2, "Средний"));
        priorities.add(new RequirementPriorityDTO(3,"Высокий"));
        pageVariables.put("priorities", priorities);

        //pageVariables.put("requirements", RequirementAPI.getRequirementsByProject(Long.parseLong(projectId)));
        List<RequirementDTO> requirements = new ArrayList<>();

        requirements.add(new RequirementDTO(1, "Требование 1","Необходимо обновить функциональность ПО для корректной работы справочной информации",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 2","Необходимо обновить справочные данные",1,1,1,"21-03-2019","21-03-2019",1, 1, true));
        requirements.add(new RequirementDTO(1,"Требование 3","Требование к обновлению справочных данных",1,1,1,"21-03-2019","21-03-2019",1, 1, true));

        pageVariables.put("requirements", requirements );

        List<RequirementTypeDTO> types = new ArrayList<>();

        types.add(new RequirementTypeDTO(1,"Бизнес-требование"));
        types.add(new RequirementTypeDTO(2,"Требование пользователей"));
        types.add(new RequirementTypeDTO(3,"Функциональное требование"));
        types.add(new RequirementTypeDTO(4,"Нефункциональное требование"));

        //pageVariables.put("types", RequirementAPI.getRequirementTypes());
        pageVariables.put("types", types);

        return pageVariables;
    }
    }
