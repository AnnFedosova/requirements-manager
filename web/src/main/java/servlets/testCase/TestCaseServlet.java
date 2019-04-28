package servlets.testCase;

import api.*;
import dto.*;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TestCase", urlPatterns = "/testCase")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class TestCaseServlet extends HttpServlet {
    TestCaseServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testCaseId = request.getParameter("testCaseId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testCaseId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCase/testCase.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testCaseId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //Principal user = request.getUserPrincipal(); todo заменить коментарии, это тестовые данные
        pageVariables.put("isAdmin", true /* когда сервер починится UserAPI.isAdmin(user.getName()) */);
        //TestCaseDTO testCase = TestCaseAPI.getTestCase(testCaseId);
        TestCaseDTO testCase = new TestCaseDTO(
                1, 1, "Test-case 1 functional",
                "", "","","","", 1);
        pageVariables.put("testCase", testCase);
        //UserDTO creator = UserAPI.getUser(testCase.getCreatorId());
        UserDTO creator = new UserDTO("Ann", "Fedosova", "", "AnnFedosova", "");
        pageVariables.put("creator", creator);

        //List<TestCaseResultDTO> testCaseResults = TestCaseResultAPI.getTestCaseResultsByTestCaseId(testCaseId);
        List<TestCaseResultDTO> testCaseResults = new ArrayList<>();
        TestCaseResultDTO testCaseResult = new TestCaseResultDTO(
                1, "10-10-2019",
                "Тест пройден успешно. Дефектов не обнаружено.",
                1, 1);
        testCaseResults.add(testCaseResult);
        pageVariables.put("testCaseResults", testCaseResults);

        //List<RequirementDTO> requirements = TestCaseAPI.getReqByTestCaseId(testCaseId);
        //List<RequirementDTO> requirements = RequirementAPI.getRequirementsByProject(1);
        List<RequirementDTO> requirements = new ArrayList<>();
        pageVariables.put("requirements", requirements);

        return pageVariables;
    }
}

