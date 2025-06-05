package io.envoi.model;

import java.time.LocalDateTime;

/**
 * Contains raw data from .log files for each log entry.
 * */
public class LogEntry {
    private LocalDateTime dateTime;
    private String userId;
    private String command;
    private String args;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "LogEntry{" + "dateTime=" + dateTime + ", userId='" + userId + '\'' + ", command='" + command + '\'' + ", args=" + args + '}';
    }
}
