package servlets.testCase;

import api.APIActions;
import api.TestCaseAPI;
import api.TestPlanAPI;
import api.TestSuiteAPI;
import dto.*;
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

@WebServlet(name = "AddTestCaseToTestSuite", urlPatterns = "/addTestCaseToTestSuite")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class AddTestCaseToTestSuiteServlet extends HttpServlet {

    AddTestCaseToTestSuiteServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testSuiteId = request.getParameter("testSuiteId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testSuiteId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCase/addTestCaseToTestSuite.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");

        String testCaseId = httpRequest.getParameter("testCases");
        String testSuiteId = httpRequest.getParameter("testSuiteId");

        if (testCaseId == null || testSuiteId == null) {
            httpResponse.getWriter().println("Not added");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            TestCaseTestSuiteDTO testCaseTestSuite = new TestCaseTestSuiteDTO(testCaseId, testSuiteId);
            Response response = TestSuiteAPI.addTestCaseToTestSuite(testCaseTestSuite);
            APIActions.checkResponseStatus(response, httpResponse);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testSuiteId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true/*сервер не компилится UserAPI.isAdmin(user.getName()) */);

        //TestSuiteDTO testSuite = TestSuiteAPI.getTestSuite(testSuiteId);
        TestSuiteDTO testSuite = new TestSuiteDTO(
                1, "Набор тестов для функции генерации отчетов",
                "Совокупность (набор) тестовых вариантов, для тестирования функции генерации отчетов ",
                "2, 1", 1);

        //List<TestCaseDTO> testCases = TestCaseAPI.getTestCasesByTestPlanId(TestSuiteAPI.getTestPlanByTestSuiteId(testSuiteId).getId());
        List<TestCaseDTO> testCases = new ArrayList<>();
        TestCaseDTO testCase1 = new TestCaseDTO(
                1, 1, "Тест-кейс 1. Проверка заполнения полей",
                "20-01-2019", "1. Запустить приложение \n2. Проверить, заполнены ли поля на домашней странице","Пользователь зарегистрирован","Выйти из системы","Логин/пароль тестового пользователя: User/User", 1);
        testCases.add(testCase1);
        TestCaseDTO testCase2 = new TestCaseDTO(
                2, 1, "Интеграционный тест-кейс 2. Взаимодействие модуля А и модуля В",
                "20-01-2019", "1. Отправить приветственное сообщение из А в В\n2. Убедиться, что сообщение обработано в В","Запущены модули А и В","Завершить работу модулей","", 1);
        testCases.add(testCase2);

        pageVariables.put("testSuite", testSuite);
        pageVariables.put("testCases", testCases);

        return pageVariables;
    }
}

