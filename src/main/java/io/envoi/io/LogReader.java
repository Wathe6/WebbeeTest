package io.envoi.io;

import io.envoi.model.LogEntry;
import io.envoi.parser.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads and parses .log files from chosen directory.
 * */
public class LogReader {

    private final File inputDirectory;

    public LogReader(String inputDirPath) {
        this.inputDirectory = new File(inputDirPath);

        if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist: " + inputDirPath);
        }
    }

    public List<File> getLogFiles() {
        File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".log"));

        List<File> logFiles = new ArrayList<>();
        if (files != null) {
            logFiles.addAll(Arrays.asList(files));
        }

        return logFiles;
    }

    public List<LogEntry> readLogs(File file) {
        List<LogEntry> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Parser.parse(line).ifPresent(entries::add);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }
}