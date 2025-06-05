package io.envoi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateTimeUtil() {}

    public static LocalDateTime parse(String input) {
        return LocalDateTime.parse(input, FORMATTER);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
