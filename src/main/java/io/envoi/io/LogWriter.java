package io.envoi.io;

import io.envoi.model.LogRecord;
import io.envoi.model.UserLogs;
import io.envoi.util.DateTimeUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Writes user map to their files.
 * */
public class LogWriter {
    private final File outputDirectory;

    public LogWriter(String outputPath) {
        this.outputDirectory = new File(outputPath);
        if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
            throw new RuntimeException("Не удалось создать выходную директорию: " + outputDirectory.getAbsolutePath());
        }
    }

    public void writeAll(Map<String, UserLogs> users) {
        for (Map.Entry<String, UserLogs> entry : users.entrySet()) {
            writeSingle(entry.getValue());
        }
    }

    private void writeSingle(UserLogs user) {
        File userFile = new File(outputDirectory, user.getId() + ".log");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {

            List<LogRecord> logs = user.getLogs();

            for (LogRecord logRecord : logs) {
                String timestamp = DateTimeUtil.format(logRecord.dateTime());
                writer.write("[" + timestamp + "] " + user.getId() + " " + logRecord.logText());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Ошибка при записи файла пользователя " + user.getId() + ": " + e.getMessage());
        }
    }
}
