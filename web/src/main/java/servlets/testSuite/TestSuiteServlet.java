package servlets.testSuite;

import dto.TestCaseDTO;
import dto.TestEnvironmentDTO;
import dto.TestPlanDTO;
import dto.TestSuiteDTO;
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

@WebServlet(name = "TestSuite", urlPatterns = "/testSuite")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class TestSuiteServlet extends HttpServlet {

    TestSuiteServlet(){}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testSuiteId = request.getParameter("testSuiteId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testSuiteId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testSuite/testSuite.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long specificationId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* когда сервер починится UserAPI.isAdmin(user.getName()) */);

        TestSuiteDTO testSuite = new TestSuiteDTO(
                1, "Test-suite for print function",
                "Here are tests for report printing features for the card issuance system.",
                "2, 1", 1);
        pageVariables.put("testSuite", testSuite);

        List<TestCaseDTO> testCases = new ArrayList<>();
        TestCaseDTO testCase = new TestCaseDTO(
                1, 1, "Тест-кейс 1. Проверка заполнения полей",
                "20-01-2019", "1. Запустить приложение \n2. Проверить, заполнены ли поля на домашней странице","Пользователь зарегистрирован","Выйти из системы","Логин/пароль тестового пользователя: User/User", 1);
        testCases.add(testCase);
        TestCaseDTO testCase2 = new TestCaseDTO(
                2, 1, "Интеграционный тест-кейс 2. Взаимодействие модуля А и модуля В",
                "20-01-2019", "1. Отправить приветственное сообщение из А в В\n2. Убедиться, что сообщение обработано в В","Запущены модули А и В","Завершить работу модулей","", 1);
        testCases.add(testCase2);
        pageVariables.put("testCases", testCases);
        return pageVariables;
    }
}
