package servlets.testPlan;


import api.RequirementAPI;
import api.SpecificationAPI;
import api.UserAPI;
import dto.*;
import reportsgenerator.*;

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

@WebServlet(name = "Print_testPlan", urlPatterns = "/print_testPlan")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class PrintTestPlanServlet extends HttpServlet {

    public PrintTestPlanServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //при нажатии на кнопку отчетов попадаем сюда
        response.setContentType("text/html;charset=utf-8");
        String testPlanId = request.getParameter("testPlanId");

        //получаем массив из id тест-кейсов, они в нужном порядке
        String estCaseIdsString = (request.getParameter("testCaseIds")
                .replaceAll(" ", ""))
                .replaceAll("\"", "");
        String[] testCaseIds = estCaseIdsString.split(",");

        //получаем массив из id тестовых наборов, они в нужном порядке
        String testSuiteIdsString = (request.getParameter("testSuiteIds")
                .replaceAll(" ", ""))
                .replaceAll("\"", "");
        String[] testSuiteIds = testSuiteIdsString.split(",");

        Map<String, Object> pageVariables = null;
        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            pageVariables = createPageVariablesMap(request, Long.parseLong(testPlanId));

            // Массив байтов получившегося файла
            byte[] byteArray = reportGenerator.template(
                    new TestPlanWithInfoForReport(
                            (TestPlanDTO) pageVariables.get("testPlan"),

                            (
                                    getSortedListOfTestCases((List<TestCaseDTO>) (pageVariables.get("testCases")),
                                            testCaseIds)
                            )
                                    .stream()
                                    .map(TestCaseForReport::new)
                                    .collect(Collectors.toList()),

                            (
                                    getSortedListOfTestSuites((List<TestSuiteDTO>) (pageVariables.get("testSuites")),
                                            testSuiteIds)
                            )
                                    .stream()
                                    .map(TestSuiteForReport::new)
                                    .collect(Collectors.toList())
                    )
            );

            String testPlanName = ((TestPlanDTO) pageVariables.get("testPlan")).getName();
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-disposition", "attachment; filename=\"" + testPlanName + ".docx\"");
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
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long id) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        SpecificationDTO specification = SpecificationAPI.getSpecification(id);
        List<RequirementDTO> requirements = RequirementAPI.getRequirementsBySpecification(id);
        SpecificationWithRequirements specificationWithRequirements = new SpecificationWithRequirements();

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", UserAPI.isAdmin(user.getName()));
        pageVariables.put("specification", specification);
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }

    public static List<TestCaseDTO> getSortedListOfTestCases(List<TestCaseDTO> testCases, String[] testCaseIds){
        if (testCases.size() != testCaseIds.length){
            return testCases;
        }
        List<TestCaseDTO> SortedListOfTestCases = new ArrayList<>();
        for (String testCaseId : testCaseIds) {
            int inputTestCaseId = Integer.parseInt(testCaseId);
            for (TestCaseDTO testCase : testCases) {
                if (testCase.getId() == inputTestCaseId) {
                    SortedListOfTestCases.add(testCase);
                }
            }
        }
        return SortedListOfTestCases;
    }

    public static List<TestSuiteDTO> getSortedListOfTestSuites(List<TestSuiteDTO> testSuites, String[] testSuiteIds){
        if (testSuites.size() != testSuiteIds.length){
            return testSuites;
        }
        List<TestSuiteDTO> SortedListOfTestSuites = new ArrayList<>();
        for (String testSuiteId : testSuiteIds) {
            int inputTestSuiteId = Integer.parseInt(testSuiteId);
            for (TestSuiteDTO testSuite : testSuites) {
                if (testSuite.getId() == inputTestSuiteId) {
                    SortedListOfTestSuites.add(testSuite);
                }
            }
        }
        return SortedListOfTestSuites;
    }
}
