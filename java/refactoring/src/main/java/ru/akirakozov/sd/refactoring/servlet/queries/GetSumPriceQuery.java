package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSumPriceQuery implements Query {
    @Override
    public String processResultSet(ResultSet rs) {
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        htmlBuilder.addLine("Summary price: ");
        try {
            if (rs.next()) {
                htmlBuilder.addLine(Integer.toString(rs.getInt(1)));
            }
        } catch (SQLException e) {
            htmlBuilder.clear();
        }
        return htmlBuilder.toString();
    }
}
