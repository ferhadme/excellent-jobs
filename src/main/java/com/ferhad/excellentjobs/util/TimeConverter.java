package com.ferhad.excellentjobs.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeConverter {
    public static Long convert(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static Long convert(LocalDate dateTime) {
        return dateTime.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static LocalDateTime convertToLocalDateTime(Long epochMilli) {
        return Instant.ofEpochMilli(epochMilli / 1000 * 1000).atOffset(ZoneOffset.UTC).toLocalDateTime();
    }

    public static LocalDate convertToLocalDate(Long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC).toLocalDate();
    }
}
