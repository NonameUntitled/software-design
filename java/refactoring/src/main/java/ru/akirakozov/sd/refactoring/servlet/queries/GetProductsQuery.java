package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductsQuery extends Query {
    @Override
    void processQuery(ResultSet rs) throws SQLException {
        while (rs.next()) {
            htmlBuilder.addProduct(Product.readFromResultSet(rs));
        }
    }
}
