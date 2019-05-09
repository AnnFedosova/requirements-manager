package servlets.testCaseResult;

import api.APIActions;
import api.TestCaseAPI;
import api.TestCaseResultAPI;
import dto.TestCaseDTO;
import dto.TestCaseResultDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "Edit_TestCaseResult", urlPatterns = "/edit_testCaseResult")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditTestCaseResultServlet extends HttpServlet {

    public EditTestCaseResultServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String testCaseResultId = request.getParameter("testCaseResultId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testCaseResultId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCaseResult/editTestCaseResult.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testCaseId = httpRequest.getParameter("testCaseId");
        String name = httpRequest.getParameter("name");
        String plan = httpRequest.getParameter("plan");
        String startConditions = httpRequest.getParameter("startConditions");
        String endConditions = httpRequest.getParameter("endConditions");
        String data = httpRequest.getParameter("data");

        if (name == null || testCaseId == null) {
            httpResponse.getWriter().println("Not created");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestCaseDTO testCase = TestCaseAPI.getTestCase(Long.parseLong(testCaseId));
            testCase.setName(name);
            testCase.setPlan(plan);
            testCase.setStartConditions(startConditions);
            testCase.setEndConditions(endConditions);
            testCase.setData(data);

            Response response = TestCaseAPI.editTestCase(testCase);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testCaseResultId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //TestCaseResultDTO testCaseResult = TestCaseResultAPI.getTestCaseResult(testCaseResultId);
        TestCaseResultDTO testCaseResult = new TestCaseResultDTO(
                1, "10-10-2019",
                "Тест пройден успешно. Дефектов не обнаружено.",
                1, 1);
        pageVariables.put("testCaseResult", testCaseResult);

        //List<UserDTO> users = ProjectAPI.getUsersByProject(id);
        List<UserDTO> testers = new ArrayList<>();
        testers.add(new UserDTO("Иван", "Петров", "", "IPetrov","87654321"));
        testers.add(new UserDTO("Ольга", "Иванова", "", "OIvanova","87654321"));

        pageVariables.put("testers", testers);
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* сервер лежит UserAPI.isAdmin(user.getName()) */);

        return pageVariables;
    }
}