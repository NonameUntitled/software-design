package ru.akirakozov.sd.refactoring.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtilsTest {
    public static void clearDatabase() {
        String cleanRequest = "drop table if exists product";
        String createRequest = """
                create table if not exists product(
                    id integer primary key autoincrement not null,
                    name text not null,
                    price int not null
                )
                """;

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();

            stmt.executeUpdate(cleanRequest);
            stmt.executeUpdate(createRequest);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}