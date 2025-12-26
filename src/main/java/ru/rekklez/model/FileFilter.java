package ru.rekklez.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileFilter {

    private final List<Integer> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();
    private final Config config;

    public FileFilter(Config config) {
        this.config = config;
    }

    public void run() throws IOException {
        filter();

        createOutputDestination();

        fillIntegerFile();
        fillFloatFile();
        fillStringFile();

        printStatistic();
    }

    private void createOutputDestination() throws IOException {
        Files.createDirectories(Path.of(this.config.getOutput()));
    }

    private void filter() {
        for (String file : this.config.getFiles()) {
            try (Stream<String> lines = Files.lines(Path.of(file))) {
                lines.forEach(line -> {
                    Scanner scanner = new Scanner(line).useLocale(Locale.US);
                    if (scanner.hasNextInt()) {
                        this.integers.add(scanner.nextInt());
                    } else if (scanner.hasNextDouble()) {
                        this.floats.add(scanner.nextDouble());
                    } else {
                        this.strings.add(scanner.nextLine());
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fillIntegerFile() {
        Path outputPath = Path.of(config.getOutput(), config.getPrefix() + "integers.txt");
        if (!this.integers.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(outputPath.toFile(), config.isAppendable()))) {
                for (int num : this.integers) {
                    pw.println(num);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fillFloatFile() {
        Path outputPath = Path.of(config.getOutput(), config.getPrefix() + "floats.txt");
        if (!this.floats.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(outputPath.toFile(), config.isAppendable()))) {
                for (double num : this.floats) {
                    pw.println(num);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fillStringFile() {
        Path outputPath = Path.of(config.getOutput(), config.getPrefix() + "strings.txt");
        if (!this.strings.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(outputPath.toFile(), config.isAppendable()))) {
                for (String str : this.strings) {
                    pw.println(str);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printStatistic() {

    }

    @Override
    public String toString() {
        return "FileFilter{" +
                "integers=" + integers +
                ", floats=" + floats +
                ", strings=" + strings +
                '}';
    }
}
