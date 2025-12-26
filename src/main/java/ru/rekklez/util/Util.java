package ru.rekklez.util;

import ru.rekklez.model.Config;
import ru.rekklez.model.Option;
import ru.rekklez.util.exception.DuplicateDescriptionOptionException;
import ru.rekklez.util.exception.DuplicateOptionException;
import ru.rekklez.util.exception.InvalidOptionException;
import ru.rekklez.util.exception.InvalidValueException;

import java.io.*;
import java.util.*;

public class Util {

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

}
