package servlets;


import api.ReleaseAPI;
import api.RequirementAPI;
import api.UserAPI;
import dto.ReleaseDTO;
import dto.RequirementDTO;
import reportsgenerator.ReleaseWithRequirements;
import reportsgenerator.ReportGenerator;
import reportsgenerator.RequirementForReport;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "Print_release", urlPatterns = "/print_release")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class PrintReleaseServlet extends HttpServlet {

    public PrintReleaseServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //при нажатии на кнопку отчетов попадаем сюда
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("releaseId");

        //получаем массив из id требований, они в нужном порядке
        String requirementIdsString = (request.getParameter("requirementIds")
                .replaceAll(" ", ""))
                .replaceAll("\"", "");
        String[] requirementIds = requirementIdsString.split(",");

        Map<String, Object> pageVariables = null;
        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            pageVariables = createPageVariablesMap(request, Long.parseLong(id));
            // Массив байтов получившегося файла
            byte[] byteArray = reportGenerator.template(
                    new ReleaseWithRequirements(
                            (ReleaseDTO) pageVariables.get("release"),
                            (
                                    PrintSpecificationServlet.getSortedListOfRequirements((List<RequirementDTO>) (pageVariables.get("requirements")),
                                            requirementIds)
                            )
                                    .stream()
                                    .map(RequirementForReport::new)
                                    .collect(Collectors.toList()
                                    )
                    )
            );

            String releaseName = ((ReleaseDTO) pageVariables.get("release")).getName();
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-disposition", "attachment; filename=\"" + releaseName + ".docx\"");
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
        ReleaseDTO release = ReleaseAPI.getRelease(id);
        List<RequirementDTO> requirements = RequirementAPI.getRequirementsByRelease(id);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("release", release);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }
}
