package servlets.testCaseResult;

import api.UserAPI;
import dto.TestCaseResultDTO;
import dto.TestEnvironmentComponentDTO;
import dto.TestEnvironmentDTO;
import dto.UserDTO;
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

@WebServlet(name = "TestCaseResult", urlPatterns = "/testCaseResult")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin", "user"}))
public class TestCaseResultServlet extends HttpServlet {
    TestCaseResultServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String testCaseResultId = request.getParameter("testCaseResultId");

        Map<String, Object> pageVariables = null;
        try {
            pageVariables = createPageVariablesMap(request, Long.parseLong(testCaseResultId));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.getInstance().getPage("testCaseResult/testCaseResult.html", pageVariables));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error!  " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, long testCaseResultId) throws Exception {
        Map<String, Object> pageVariables = new HashMap<>();
        //Principal user = request.getUserPrincipal();
        pageVariables.put("isAdmin", true /* todo когда сервер починится UserAPI.isAdmin(user.getName()) */);

        TestCaseResultDTO testCaseResult = new TestCaseResultDTO(
                1, "10-10-2019",
                "Тест пройден успешно. Дефектов не обнаружено.",
                1, 1);
        pageVariables.put("pageVariables", testCaseResult);

        //UserDTO tester = UserAPI.getUser(testCaseResult.getTesterId());
        UserDTO tester = new UserDTO("Ann", "Fedosova", "", "AnnFedosova", "");
        pageVariables.put("tester", tester);

        return pageVariables;
    }

    }


