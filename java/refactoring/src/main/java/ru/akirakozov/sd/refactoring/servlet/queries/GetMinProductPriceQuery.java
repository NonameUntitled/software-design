package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMinProductPriceQuery extends Query {
    @Override
    void processQuery(ResultSet rs) throws SQLException {
        htmlBuilder.addHeader("Product with min price: ");
        while (rs.next()) {
            htmlBuilder.addProduct(Product.readFromResultSet(rs));
        }
    }
}
