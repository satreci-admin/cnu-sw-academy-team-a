package com.example.simplerpa;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JdbcUtils {

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
