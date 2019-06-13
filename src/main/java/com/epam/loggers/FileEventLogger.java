package com.epam.loggers;

import com.epam.entities.Event;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEventLogger implements EventLogger {

    private String filename;
    private Path path;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) {
        path = Paths.get(filename);
        try {
            FileUtils.writeStringToFile(path.toFile(), event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        this.path = Paths.get(filename);
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        if (!Files.isWritable(path)) {
            throw new IOException();
        }
    }
}
