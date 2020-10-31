package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.Product;
import ru.akirakozov.sd.refactoring.html.HtmlBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductsNumberQuery implements Query {

    @Override
    public String processResultSet(ResultSet rs) {
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        htmlBuilder.addLine("Number of products: ");
        try {
            if (rs.next()) {
                htmlBuilder.addLine(Integer.toString(rs.getInt(1)));
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
        return htmlBuilder.toString();
    }
}
