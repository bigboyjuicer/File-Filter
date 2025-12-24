package ru.rekklez.util;

import ru.rekklez.model.Options;
import ru.rekklez.model.ParsedArguments;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileFilterUtil {

    public static ParsedArguments parseArgs(String[] args) throws DuplicateDescriptionOptionException {
        ParsedArguments parsedArguments = new ParsedArguments();
        Options options = new Options();
        int argsOffset = 0;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s" -> {
                    options.setShortDesc(true);
                    argsOffset = i;
                }
                case "-f" -> {
                    options.setFullDesc(true);
                    argsOffset = i;
                }
                case "-a" -> {
                    options.setAppendable(true);
                    argsOffset = i;
                }
                case "-o" -> {
                    options.setResultPath(args[++i]);
                    argsOffset = i;
                }
                case "-p" -> {
                    options.setPrefix(args[++i]);
                    argsOffset = i;
                }
            }
        }
        parsedArguments.setOptions(options);
        parsedArguments.setFiles(Arrays.copyOfRange(args, argsOffset + 1, args.length));

        return parsedArguments;
    }

    public static void filter(ParsedArguments parsedArguments) {

    }

}
