package ru.rekklez.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LazyWriter implements Closeable {

    private final Path path;
    private final boolean append;
    private BufferedWriter writer;

    public LazyWriter(Path path, boolean append) {
        this.path = path;
        this.append = append;
    }

    public void write(String line) throws IOException {
        if (writer == null) {
            writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING);
        }
        writer.write(line);
        writer.newLine();
    }

    @Override
    public void close() throws IOException {
        if(writer != null) {
            writer.close();
        }
    }
}
