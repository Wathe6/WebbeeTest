package io.envoi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class UserLogs {
    private String id;
    private double balance;
    private NavigableMap<LocalDateTime, List<String>> logs;
    public UserLogs(String userId, double b) {
        id = userId;
        balance = b;
        logs = new TreeMap<>();
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }
    public NavigableMap<LocalDateTime, List<String>> getLogs() {
        return logs;
    }

    public void inquiry(double b) {
        balance = b;
    }
    /**
     * transferred to me
     * */
    public void transferredTo(double sum) {
        balance += sum;
    }
    /**
     * transferred from me
     * */
    public void transferredFrom(double sum) {
        balance -= sum;
    }

    public void withdrew(double sum) {
        balance -= sum;
    }

    public void add(LocalDateTime dateTime, String message) {
        logs.computeIfAbsent(dateTime, k -> new ArrayList<>()).add(message);
    }
}