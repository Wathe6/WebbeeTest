package io.envoi.model;

import java.time.LocalDateTime;

/**
 * Contains date and row text from logs, which was already parsed.
 * */
public record LogRecord(LocalDateTime dateTime, String logText) {}
