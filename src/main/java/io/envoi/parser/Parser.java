package io.envoi.parser;

import io.envoi.model.LogEntry;
import io.envoi.util.DateTimeUtil;
import io.envoi.util.LogPatterns;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * Gets LogEntry out of line.
 * */
public final class Parser {
    public static Optional<LogEntry> parse(String line) {
        Matcher baseMatcher = LogPatterns.COMMON_PATTERN.matcher(line);
        if (!baseMatcher.matches()) return Optional.empty();

        try {
            LocalDateTime dateTime = DateTimeUtil.parse(baseMatcher.group("datetime"));
            String user = baseMatcher.group("user");
            String command = baseMatcher.group("command");
            String argsRaw = baseMatcher.group(4);

            LogEntry entry = new LogEntry();
            entry.setDateTime(dateTime);
            entry.setUserId(user);
            entry.setCommand(command);
            entry.setArgs(argsRaw);

            return Optional.of(entry);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}