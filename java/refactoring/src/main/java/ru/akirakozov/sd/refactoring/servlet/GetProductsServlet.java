package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.Database;
import ru.akirakozov.sd.refactoring.http.ServletUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = Database.executeQuery("get_all");
        ServletUtils.ResponsePostProcessing(response, result);
    }
}
