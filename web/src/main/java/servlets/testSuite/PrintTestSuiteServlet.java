package servlets.testSuite;


import dto.TestCaseDTO;
import dto.TestPlanDTO;
import dto.TestSuiteDTO;
import reportsgenerator.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "Print_testSuite", urlPatterns = "/print_testSuite")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class PrintTestSuiteServlet extends HttpServlet {

    public PrintTestSuiteServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //при нажатии на кнопку отчетов попадаем сюда
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String testSuiteId = request.getParameter("testSuiteId");

        //получаем массив из id тест-кейсов, они в нужном порядке
        String testCaseIdsString = (request.getParameter("testCaseIds")
                .replaceAll(" ", ""))
                .replaceAll("\"", "");
        String[] testCaseIds = testCaseIdsString.split(",");

        Map<String, Object> pageVariables = null;
        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            pageVariables = createPageVariablesMap(request, Long.parseLong(testSuiteId));

            // Массив байтов получившегося файла
            byte[] byteArray = reportGenerator.template(
                    new TestSuiteWithInfoForReport(
                            (TestSuiteForReport) pageVariables.get("testSuite"),

                            (
                                    getSortedListOfTestCases((List<TestCaseForReport>) (pageVariables.get("testCases")),
                                            testCaseIds)
                            )
                                    .stream()
                                    .collect(Collectors.toList())
                    )
            );

            String testSuiteName = ((TestSuiteForReport) pageVariables.get("testSuite")).getName();
            testSuiteName = URLEncoder.encode(testSuiteName,"UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-disposition", "attachment; filename=\"" + testSuiteName + ".docx\"");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(byteArray);
            servletOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testSuiteId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();

        //TestSuiteDTO testSuite = TestSuiteAPI.getTestSuite(testSuiteId); //ждем, пока сервер заработает
        TestSuiteDTO testSuite = new TestSuiteDTO(
                1, "Набор тестов для функции генерации отчетов",
                "Совокупность (набор) тестовых вариантов, для тестирования функции генерации отчетов ",
                "2, 1", 1);

        //List<TestCaseDTO> testCases = TestCaseAPI.getTestCasesByTestPlanId(testPlanId);
        List<TestCaseForReport> testCases = new ArrayList<>();
        TestCaseDTO testCase = new TestCaseDTO(
                1, 1, "Тест-кейс 1. Проверка заполнения полей",
                "20-01-2019", "1. Запустить приложение \n2. Проверить, заполнены ли поля на домашней странице","Пользователь зарегистрирован","Выйти из системы","Логин/пароль тестового пользователя: User/User", 1);
        TestCaseDTO testCase2 = new TestCaseDTO(
                2, 1, "Интеграционный тест-кейс 2. Взаимодействие модуля А и модуля В",
                "20-01-2019", "1. Отправить приветственное сообщение из А в В\n2. Убедиться, что сообщение обработано в В","Запущены модули А и В","Завершить работу модулей","", 1);
        testCases.add(new TestCaseForReport(testCase));
        testCases.add(new TestCaseForReport(testCase2));

        Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true ); //UserAPI.isAdmin(user.getName()));
        pageVariables.put("testSuite", new TestSuiteForReport(testSuite));
        pageVariables.put("testCases", testCases);

        return pageVariables;
    }

    public static List<TestCaseForReport> getSortedListOfTestCases(List<TestCaseForReport> testCases, String[] testCaseIds){
        if (testCases.size() != testCaseIds.length){
            return testCases;
        }
        List<TestCaseForReport> SortedListOfTestCases = new ArrayList<>();
        for (String testCaseId : testCaseIds) {
            int inputTestCaseId = Integer.parseInt(testCaseId);
            for (TestCaseForReport testCase : testCases) {
                if (testCase.getId() == inputTestCaseId) {
                    SortedListOfTestCases.add(testCase);
                }
            }
        }
        return SortedListOfTestCases;
    }

}
