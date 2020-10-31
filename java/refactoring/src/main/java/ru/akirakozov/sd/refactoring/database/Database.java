package ru.akirakozov.sd.refactoring.database;

import ru.akirakozov.sd.refactoring.Product;
import ru.akirakozov.sd.refactoring.servlet.queries.*;

import java.sql.*;

public class Database {
    private static final String DATABASE_URL = "jdbc:sqlite:test.db";

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

    public static void dropTable(String tableName) {
        try (Connection c = DriverManager.getConnection(DATABASE_URL)) {
            String sql = "drop table if exists %s".formatted(tableName);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public static void addProduct(Product product) {
        String sql = "insert into product (name, price) values (\"%s\", %d)".formatted(product.name(), product.price());
        executeUpdate(sql);
    }

    public static String getProductsInfo() {
        String sql = "SELECT * FROM PRODUCT";
        return executeQueryWithResponse(sql, new GetProductsQuery());
    }

    public static String getMaxPriceInfo() {
        String sql = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
        return executeQueryWithResponse(sql, new GetMaxProductPriceQuery());
    }

    public static String getMinPriceInfo() {
        String sql = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
        return executeQueryWithResponse(sql, new GetMinProductPriceQuery());
    }

    public static String getSumPriceInfo() {
        String sql = "SELECT SUM(price) FROM PRODUCT";
        return executeQueryWithResponse(sql, new GetSumPriceQuery());
    }

    public static String getProductsNumberInfo() {
        String sql = "SELECT COUNT(*) FROM PRODUCT";
        return executeQueryWithResponse(sql, new GetProductsNumberQuery());
    }
}
