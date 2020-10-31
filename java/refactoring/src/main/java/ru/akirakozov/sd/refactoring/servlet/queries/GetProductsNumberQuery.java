package ru.akirakozov.sd.refactoring.servlet.queries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductsNumberQuery extends Query {
    @Override
    void processQuery(ResultSet rs) throws SQLException {
        htmlBuilder.addLine("Number of products: ");
        if (rs.next()) {
            htmlBuilder.addLine(Integer.toString(rs.getInt(1)));
        }
    }
}
