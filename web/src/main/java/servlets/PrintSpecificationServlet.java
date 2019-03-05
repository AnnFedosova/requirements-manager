package servlets;


import api.RequirementAPI;
import api.SpecificationAPI;
import api.UserAPI;
import dto.RequirementDTO;
import dto.SpecificationDTO;
import reportsgenerator.ReportGenerator;
import reportsgenerator.RequirementForReport;
import reportsgenerator.SpecificationWithRequirements;

import javax.servlet.ServletOutputStream;
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
import java.util.stream.Collectors;

@WebServlet(name = "Print_specification", urlPatterns = "/print_specification")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class PrintSpecificationServlet extends HttpServlet {

    public PrintSpecificationServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //при нажатии на кнопку отчетов попадаем сюда
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("specificationId");

        //получаем массив из id требований, они в нужном порядке
        String requirementIdsString = (request.getParameter("requirementIds")
                .replaceAll(" ", ""))
                .replaceAll("\"", "");
        String[] requirementIds = requirementIdsString.split(",");

        Map<String, Object> pageVariables = null;
        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));

/*
* byte[] byteArray = reportGenerator.template(
                    new SpecificationWithRequirements(
                            (SpecificationDTO) pageVariables.get("specification"),

                            ((List<RequirementDTO>) (pageVariables.get("requirements")))
                                    .stream()
                                    .map(RequirementForReport::new)
                                    .collect(Collectors.toList())
                    )
            );
*
*
* */



            // Массив байтов получившегося файла
            byte[] byteArray = reportGenerator.template(
                    new SpecificationWithRequirements(
                            (SpecificationDTO) pageVariables.get("specification"),

                            (
                                    getSortedListOfRequirements((List<RequirementDTO>) (pageVariables.get("requirements")),
                                            requirementIds)
                            )
                                    .stream()
                                    .map(RequirementForReport::new)
                                    .collect(Collectors.toList())
                    )
            );
            String specificationName = ((SpecificationDTO) pageVariables.get("specification")).getName();
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-disposition", "attachment; filename=\"" + specificationName + ".docx\"");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(byteArray);
            servletOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //pageVariables = createPageVariablesMap(request);
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
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long id) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        SpecificationDTO specification = SpecificationAPI.getSpecification(id);
        List<RequirementDTO> requirements = RequirementAPI.getRequirementsBySpecification(id);
        SpecificationWithRequirements specificationWithRequirements = new SpecificationWithRequirements();

        Integer[] ids2 = null;
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("specification", specification);

        pageVariables.put("specificationWithRequirements", specificationWithRequirements);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }

    private List<RequirementDTO> getSortedListOfRequirements(List<RequirementDTO> requirements, String[] requirementIds){
        if (requirements.size() != requirementIds.length){
            return requirements;
        }
        List<RequirementDTO> SortedListOfRequirements = new ArrayList<>();
        for (String requirementId : requirementIds) {
            int inputReqId = Integer.parseInt(requirementId);
            for (RequirementDTO requirement : requirements) {
                if (requirement.getId() == inputReqId) {
                    SortedListOfRequirements.add(requirement);
                }
            }

        }
        return SortedListOfRequirements;
    }
}
