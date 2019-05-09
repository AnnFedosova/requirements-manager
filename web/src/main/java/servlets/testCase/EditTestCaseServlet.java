package servlets.testCase;

import api.APIActions;
import api.TestCaseAPI;
import dto.TestCaseDTO;
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


@WebServlet(name = "Edit_TestCase", urlPatterns = "/edit_testCase")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class EditTestCaseServlet extends HttpServlet {

    public EditTestCaseServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String testCaseId = request.getParameter("testCaseId");
        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testCaseId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCase/editTestCase.html", pageVariables));
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

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testCaseId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //TestCaseDTO testCase = TestCaseAPI.getTestCase(testCaseId);
        TestCaseDTO testCase = new TestCaseDTO(
                1, 1, "Тест-кейс 1. Проверка заполнения полей",
                "20-01-2019", "1. Запустить приложение \n2. Проверить, заполнены ли поля на домашней странице","Пользователь зарегистрирован","Выйти из системы","Логин/пароль тестового пользователя: User/User", 1);
        pageVariables.put("testCase", testCase);

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* сервер лежит UserAPI.isAdmin(user.getName()) */);

        return pageVariables;
    }
}