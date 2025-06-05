package io.envoi.model;

import java.time.LocalDateTime;

public class LogEntry {
    private LocalDateTime dateTime;
    private String userId;
    private String command;
    private String args;
}
