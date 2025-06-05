package io.envoi.controller;

import io.envoi.io.LogReader;
import io.envoi.io.LogWriter;
import io.envoi.model.LogEntry;
import io.envoi.model.UserLogs;
import io.envoi.util.LogPatterns;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * Main controller for all project logic.
 * */
public class LogController {

    private final LogReader reader;
    private final LogWriter writer;
    private Map<String, UserLogs> users;

    public LogController(String inputPath, String outputPath) {
        this.reader = new LogReader(inputPath);
        this.writer = new LogWriter(outputPath);
        this.users = new HashMap<>();
    }

    public void start() {
        List<LogEntry> logEntries = parseLogs();

        for (int i = 0; i < logEntries.size(); i++) {
            applyEntry(logEntries.get(i));
            logEntries.set(i, null);
        }

        users.values().forEach(UserLogs::finalBalance);
        writer.writeAll(users);
    }

    private List<LogEntry> parseLogs() {
        return reader.getLogFiles().stream()
                .flatMap(file -> reader.readLogs(file).stream())
                .sorted(Comparator.comparing(LogEntry::getDateTime))
                .collect(Collectors.toList());
    }

    private void applyEntry(LogEntry log) {
        UserLogs user = users.computeIfAbsent(log.getUserId(), UserLogs::new);

        switch (log.getCommand()) {
            case "balance inquiry" -> handleBalanceInquiry(user, log);
            case "transferred" -> handleTransfer(user, log);
            case "withdrew" -> handleWithdraw(user, log);
        }

        user.add(log.getDateTime(), log.getCommand() + log.getArgs());
    }

    private void handleBalanceInquiry(UserLogs user, LogEntry log) {
        Matcher matcher = LogPatterns.BALANCE_PATTERN.matcher(log.getArgs().trim());

        if (matcher.matches()) {
            user.inquiry(Double.parseDouble(matcher.group("amount")));
        }
    }

    private void handleTransfer(UserLogs sender, LogEntry log) {
        Matcher matcher = LogPatterns.TRANSFER_PATTERN.matcher(log.getArgs().trim());

        if (matcher.matches()) {
            double amount = Double.parseDouble(matcher.group("amount"));
            String recipientId = matcher.group("targetUser");

            sender.transferred(amount);
            UserLogs recipient = users.computeIfAbsent(recipientId, UserLogs::new);
            recipient.received(amount);
            recipient.add(log.getDateTime(), "received " + amount + " from " + sender.getId());
        }
    }

    private void handleWithdraw(UserLogs user, LogEntry log) {
        Matcher matcher = LogPatterns.WITHDREW_PATTERN.matcher(log.getArgs().trim());

        if (matcher.matches()) {
            user.withdrew(Double.parseDouble(matcher.group("amount")));
        }
    }
}