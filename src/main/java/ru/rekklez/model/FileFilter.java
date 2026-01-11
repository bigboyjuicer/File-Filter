package ru.rekklez.model;

import ru.rekklez.util.exception.NoFilesException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class FileFilter {

    private final Config config;
    private final Statistic statistic;

    public FileFilter(Config config) {
        this.config = config;
        this.statistic = new Statistic();
    }

    public void run() throws IOException, NoFilesException {
        createOutputDestination();

        filter();

        printStatistic();
    }

    private void createOutputDestination() throws IOException {
        if(!Files.exists(Path.of(this.config.getOutput()))) Files.createDirectories(Path.of(this.config.getOutput()));
    }

    private void filter() throws NoFilesException, IOException {
        if (this.config.getFiles().isEmpty())
            throw new NoFilesException("Нет файлов для фильтрации. Невозможно продолжить выполнение программы.");

        try (LazyWriter intWriter = new LazyWriter(Path.of(config.getOutput(), config.getPrefix() + "integers.txt"), config.isAppendable());
             LazyWriter floatWriter = new LazyWriter(Path.of(config.getOutput(), config.getPrefix() + "floats.txt"), config.isAppendable());
             LazyWriter stringWriter = new LazyWriter(Path.of(config.getOutput(), config.getPrefix() + "strings.txt"), config.isAppendable())
        ) {
            for (String file : this.config.getFiles()) {
                try (Stream<String> lines = Files.lines(Path.of(file))) {
                    lines.forEach(line -> classifyAndWrite(line, intWriter, floatWriter, stringWriter));
                } catch (NoSuchFileException e) {
                    System.out.println("Файл с именем " + e.getMessage() + " не был найден.\n");
                } catch (IOException e) {
                    System.err.printf("Произошла ошибка во время обработки файла %s: %s", file, e.getMessage());
                }
            }
        }
    }

    private void classifyAndWrite(String line, LazyWriter intWriter, LazyWriter floatWriter, LazyWriter stringWriter) {
        if(line == null || line.isBlank()) return;

        try {
            if(tryWriteLong(line, intWriter)) return;
            if(tryWriteDouble(line, floatWriter)) return;

            stringWriter.write(line.trim());
            statistic.addString(line.length());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    private boolean tryWriteLong(String line, LazyWriter writer) throws IOException {
        try {
            long value = Long.parseLong(line.trim());
            writer.write(line);
            statistic.addInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean tryWriteDouble(String line, LazyWriter writer) throws IOException {
        try {
            double value = Double.parseDouble(line.trim());
            writer.write(line);
            statistic.addFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void printStatistic() {
        StatisticPrinter printer = new StatisticPrinter(config.isShortDesc(), config.isFullDesc());
        printer.printStatistic(statistic);
    }
}
