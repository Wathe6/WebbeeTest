package io.envoi.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class LogReader {

    private final File inputDirectory;

    public LogReader(String inputDirPath) {
        this.inputDirectory = new File(inputDirPath);
        if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist: " + inputDirPath);
        }
    }

    /**
     * Возвращает список .log файлов из директории input
     */
    public List<File> getLogFiles() {
        File[] files = inputDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".log");
            }
        });

        List<File> logFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                logFiles.add(file);
            }
        }
        return logFiles;
    }
}