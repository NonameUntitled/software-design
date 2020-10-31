package ru.akirakozov.sd.refactoring.servlet.queries;

import java.sql.ResultSet;

public interface Query {
    String processResultSet(ResultSet rs);
}
