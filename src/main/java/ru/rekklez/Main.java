package ru.rekklez;

import ru.rekklez.model.Config;
import ru.rekklez.util.exception.DuplicateDescriptionOptionException;
import ru.rekklez.util.exception.DuplicateOptionException;
import ru.rekklez.util.FileFilterUtil;
import ru.rekklez.util.exception.InvalidOptionException;
import ru.rekklez.util.exception.InvalidValueException;

import java.nio.file.Path;


public class Main {
    static void main(String[] args) {
        try {
            Config config = FileFilterUtil.parseArgs(args);
            System.out.println(config);
        } catch (DuplicateOptionException | DuplicateDescriptionOptionException | InvalidValueException |
                 InvalidOptionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
