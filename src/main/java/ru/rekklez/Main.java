package ru.rekklez;

import ru.rekklez.model.ParsedArguments;
import ru.rekklez.util.DuplicateDescriptionOptionException;
import ru.rekklez.util.FileFilterUtil;

public class Main {
    static void main(String[] args) {

        try {
            ParsedArguments parsedArguments = FileFilterUtil.parseArgs(args);
        } catch (DuplicateDescriptionOptionException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
