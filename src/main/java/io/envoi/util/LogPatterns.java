package io.envoi.util;

import java.util.regex.Pattern;

/**
 * Contains patterns for String matching.
 * */
public final class LogPatterns {
    private LogPatterns() {}

    public static final Pattern COMMON_PATTERN = Pattern.compile(
            "\\[(?<datetime>[^\\]]+)]\\s+(?<user>\\w+)\\s+(?<command>balance inquiry|transferred|withdrew)(.*)"
    );

    public static final Pattern BALANCE_PATTERN = Pattern.compile(
            "(?<amount>\\d+(\\.\\d{1,2})?)"
    );

    public static final Pattern TRANSFER_PATTERN = Pattern.compile(
            "(?<amount>\\d+(\\.\\d{1,2})?)\\s+to\\s+(?<targetUser>\\w+)"
    );

    public static final Pattern WITHDREW_PATTERN = Pattern.compile(
            "(?<amount>\\d+(\\.\\d{1,2})?)"
    );
}