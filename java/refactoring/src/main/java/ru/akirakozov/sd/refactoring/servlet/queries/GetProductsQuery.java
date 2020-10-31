package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.Product;
import ru.akirakozov.sd.refactoring.html.HtmlBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductsQuery implements Query {
    @Override
    public String processResultSet(ResultSet rs) {
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        try {
            while (rs.next()) {
                htmlBuilder.addProduct(Product.readFromResultSet(rs));
            }
        } catch (SQLException e) {
            htmlBuilder.clear();
        }
        return htmlBuilder.toString();
    }
}
