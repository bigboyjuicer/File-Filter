package ru.rekklez.util;

import ru.rekklez.model.Config;
import ru.rekklez.model.Option;
import ru.rekklez.util.exception.DuplicateDescriptionOptionException;
import ru.rekklez.util.exception.DuplicateOptionException;
import ru.rekklez.util.exception.InvalidOptionException;
import ru.rekklez.util.exception.InvalidValueException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class FileFilterUtil {

    public static Config parseArgs(String[] args) throws DuplicateOptionException, DuplicateDescriptionOptionException, InvalidValueException, InvalidOptionException {
        Config config = new Config();
        boolean optionsEnded = false;
        for (int i = 0; i < args.length; i++) {
            Option option = Option.getByName(args[i]);
            if (option != null) {
                if (optionsEnded) {
                    throw new InvalidOptionException("Option " + args[i] + " must be placed before file names.");
                }

                if (option.isHasValue() && i + 1 >= args.length) {
                    throw new InvalidOptionException("There is missed value for option: " + args[i]);
                }

                switch (option) {
                    case SHORT_DESC -> config.setShortDesc(true);
                    case FULL_DESC -> config.setFullDesc(true);
                    case APPEND -> config.setAppendable(true);
                    case OUTPUT -> config.setOutput(args[++i]);
                    case PREFIX -> config.setPrefix(args[++i]);
                }
            } else {
                if (args[i].startsWith("-")) {
                    throw new InvalidOptionException("Invalid option: " + args[i]);
                }

                optionsEnded = true;
                config.setFile(args[i]);
            }
        }
        return config;
    }

    private static void filter(Config config) {
        List<Integer> integers = new ArrayList<>();
        List<Double> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (String file : config.getFiles()) {
            try (Stream<String> lines = Files.lines(Path.of(file))) {
                lines.forEach(line -> {
                    Scanner scanner = new Scanner(line).useLocale(Locale.US);
                    if (scanner.hasNextInt()) {
                        integers.add(scanner.nextInt());
                    } else if (scanner.hasNextDouble()) {
                        floats.add(scanner.nextDouble());
                    } else {
                        strings.add(scanner.nextLine());
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        fillIntegerFile(config, integers);
        fillFloatFile(config, floats);
        fillStringFile(config, strings);
    }

    private static void fillIntegerFile(Config config, List<Integer> integers) {
        Path outputPath = Path.of(config.getOutput(), config.getPrefix() + "integers.txt");
        if (!integers.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(outputPath.toFile(), config.isAppendable()))) {
                for (int num : integers) {
                    pw.println(num);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void fillFloatFile(Config config, List<Double> floats) {
        Path outputPath = Path.of(config.getOutput(), config.getPrefix() + "integers.txt");
        if (!floats.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(outputPath.toFile(), config.isAppendable()))) {
                for (double num : floats) {
                    pw.println(num);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void fillStringFile(Config config, List<String> strings) {
        Path outputPath = Path.of(config.getOutput(), config.getPrefix() + "integers.txt");
        if (!strings.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(outputPath.toFile(), config.isAppendable()))) {
                for (String str : strings) {
                    pw.println(str);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
