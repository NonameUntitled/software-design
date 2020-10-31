package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMaxProductPriceQuery extends Query {
    @Override
    void processQuery(ResultSet rs) throws SQLException {
        htmlBuilder.addHeader("Product with max price: ");
        while (rs.next()) {
            htmlBuilder.addProduct(Product.readFromResultSet(rs));
        }
    }
}
