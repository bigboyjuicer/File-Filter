package ru.rekklez;

import ru.rekklez.model.Config;
import ru.rekklez.model.FileFilter;
import ru.rekklez.util.exception.DuplicateDescriptionOptionException;
import ru.rekklez.util.exception.DuplicateOptionException;
import ru.rekklez.util.Util;
import ru.rekklez.util.exception.InvalidOptionException;
import ru.rekklez.util.exception.InvalidValueException;

import java.io.IOException;

public class Main {
    static void main(String[] args) {
        try {
            Config config = Util.parseArgs(args);
            FileFilter filter = new FileFilter(config);
            filter.run();
        } catch (DuplicateOptionException | DuplicateDescriptionOptionException | InvalidValueException |
                 InvalidOptionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
