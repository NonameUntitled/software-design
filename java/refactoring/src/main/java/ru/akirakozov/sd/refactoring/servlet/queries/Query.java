package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Query {
    protected HtmlBuilder htmlBuilder = new HtmlBuilder();

    public String processResultSet(ResultSet rs) {
        try {
            processQuery(rs);
        } catch (SQLException e) {
            htmlBuilder.clear();
        }
        return htmlBuilder.toString();
    }

    abstract void processQuery(ResultSet rs) throws SQLException;
}
