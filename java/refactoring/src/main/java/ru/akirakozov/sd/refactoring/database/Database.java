package ru.akirakozov.sd.refactoring.database;

import ru.akirakozov.sd.refactoring.servlet.queries.*;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.Callable;

public class Database {
    private static final String DATABASE_URL = "jdbc:sqlite:test.db";

    private static final Map<String, Callable<String>> QUERIES = Map.of(
            "sum", Database::getSumPriceInfo,
            "max", Database::getMaxPriceInfo,
            "min", Database::getMinPriceInfo,
            "count", Database::getProductsNumberInfo,
            "get_all", Database::getAllProductsInfo
    );

    public static void createNewTable(String tableName) {
        try (Connection c = DriverManager.getConnection(DATABASE_URL)) {
            String sql = """
                    create table if not exists %s(
                    id integer primary key autoincrement not null,
                    name text not null,
                    price int not null
                    )
                    """.formatted(tableName);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String executeQuery(String type) {
        if (QUERIES.containsKey(type)) {
            try {
                return QUERIES.get(type).call();
            } catch (Exception e) {
                return "Error occurred!" + System.lineSeparator() + e.toString();
            }
        } else {
            return "Unknown command: " + type;
        }
    }

    private static void executeUpdate(String sql) {
        try {
            try (Connection c = DriverManager.getConnection(DATABASE_URL)) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String executeQueryWithResponse(String sql, Query query) {
        String response;

        try {
            try (Connection c = DriverManager.getConnection(DATABASE_URL)) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                response = query.processResultSet(rs);

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public static String addProduct(Product product) {
        String sql = "insert into product (name, price) values (\"%s\", %d)".formatted(product.name(), product.price());
        executeUpdate(sql);
        return "OK";
    }

    private static String getAllProductsInfo() {
        String sql = "select * from product";
        return executeQueryWithResponse(sql, new GetProductsQuery());
    }

    private static String getMaxPriceInfo() {
        String sql = "select * from product order by price desc limit 1";
        return executeQueryWithResponse(sql, new GetMaxProductPriceQuery());
    }

    private static String getMinPriceInfo() {
        String sql = "select * from product order by price limit 1";
        return executeQueryWithResponse(sql, new GetMinProductPriceQuery());
    }

    private static String getSumPriceInfo() {
        String sql = "select sum(price) from product";
        return executeQueryWithResponse(sql, new GetSumPriceQuery());
    }

    private static String getProductsNumberInfo() {
        String sql = "select count(*) from product";
        return executeQueryWithResponse(sql, new GetProductsNumberQuery());
    }
}
