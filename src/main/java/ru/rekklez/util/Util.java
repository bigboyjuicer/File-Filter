package ru.rekklez.util;

import ru.rekklez.model.Config;
import ru.rekklez.model.Option;
import ru.rekklez.util.exception.*;

import java.io.*;
import java.util.*;

public class Util {

    public static Config parseArgs(String[] args) throws NoArgsException {
        if(args.length == 0) throw new NoArgsException("Отсутствуют аргументы. Невозможно продолжить выполнение программы.\n");

        Config config = new Config();
        boolean optionsEnded = false;
        for (int i = 0; i < args.length; i++) {
            Option option = Option.getByName(args[i]);
            if (option != null) {
                if (optionsEnded) {
                    System.out.println("Опция " + args[i] + " должна находиться перед перечислением файлов. Данная опция будет пропущена.\n");
                    continue;
                }

                try {
                    switch (option) {
                        case SHORT_DESC -> config.setShortDesc(true);
                        case FULL_DESC -> config.setFullDesc(true);
                        case APPEND -> config.setAppendable(true);
                        case OUTPUT -> {
                            if(!(i == args.length - 1)) config.setOutput(args[++i]);
                            else System.out.printf("Пропущен параметр для опции: %s. Данная опция будет пропущена.\n", args[i]);
                        }
                        case PREFIX -> {
                            if(!(i == args.length - 1)) config.setPrefix(args[++i]);
                            else System.out.printf("Пропущен параметр для опции: %s. Данная опция будет пропущена.\n", args[i]);
                        }
                    }
                } catch (DuplicateDescriptionOptionException | InvalidValueException | DuplicateOptionException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                if (args[i].startsWith("-")) {
                    System.out.printf("Несуществующая опция: %s. Данная опция будет пропущена.\n", args[i]);
                    continue;
                }

                optionsEnded = true;
                config.setFile(args[i]);
            }
        }
        return config;
    }

}
