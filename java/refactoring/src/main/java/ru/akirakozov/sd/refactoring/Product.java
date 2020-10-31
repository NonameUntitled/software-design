package ru.akirakozov.sd.refactoring;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private final String name;
    private final Long price;

    Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public Long price() {
        return price;
    }

    public static Product readFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int price = rs.getInt("price");

        return new Product(name, Integer.toUnsignedLong(price));
    }

    public static Product readFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        return new Product(name, price);
    }
}
