package ru.akirakozov.sd.refactoring.http;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtils {
    public static void ResponsePostProcessing(HttpServletResponse response, String result) throws IOException {
        response.getWriter().print(result);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
