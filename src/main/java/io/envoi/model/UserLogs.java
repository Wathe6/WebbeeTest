package io.envoi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains and computes user balance.
 * Contains user logs sorted by date and time.
 * */
public class UserLogs {
    private String id;
    private double balance;
    private List<LogRecord> logs;

    public UserLogs(String userId) {
        id = userId;
        balance = 0;
        logs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }
    public List<LogRecord> getLogs() {
        return logs;
    }

    public void inquiry(double b) {
        balance = b;
    }
    public void received(double sum) {
        balance += sum;
    }
    public void transferred(double sum) {
        balance -= sum;
    }
    public void withdrew(double sum) {
        balance -= sum;
    }

    public void add(LocalDateTime dateTime, String message) {
        logs.add(new LogRecord(dateTime, message));
    }
    public void finalBalance() {
        logs.add(new LogRecord(logs.get(logs.size() - 1).dateTime(), "final balance " + balance));
    }
}