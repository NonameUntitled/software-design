package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.Database;
import ru.akirakozov.sd.refactoring.html.HtmlBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            String queryResult = Database.getMaxPriceInfo();
            response.getWriter().print(queryResult);
        } else if ("min".equals(command)) {
            String queryResult = Database.getMinPriceInfo();
            response.getWriter().print(queryResult);
        } else if ("sum".equals(command)) {
            String queryResult = Database.getSumPriceInfo();
            response.getWriter().print(queryResult);
        } else if ("count".equals(command)) {
            String queryResult = Database.getProductsNumberInfo();
            response.getWriter().print(queryResult);
        } else {
            response.getWriter().print("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
