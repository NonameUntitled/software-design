package ru.akirakozov.sd.refactoring.servlet.queries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSumPriceQuery extends Query {
    @Override
    void processQuery(ResultSet rs) throws SQLException {
        htmlBuilder.addLine("Summary price: ");
        if (rs.next()) {
            htmlBuilder.addLine(Integer.toString(rs.getInt(1)));
        }
    }
}
