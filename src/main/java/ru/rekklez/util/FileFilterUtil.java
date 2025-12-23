package ru.rekklez.util;

import ru.rekklez.model.Options;

public class FileFilterUtil {

    public static Options defineOptions(String[] args) throws DuplicateDescriptionOptionException {
        Options optionsBuilder = new Options();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s" -> optionsBuilder.setShortDesc(true);
                case "-f" -> optionsBuilder.setFullDesc(true);
                case "-a" -> optionsBuilder.setAppendable(true);

                //TODO: Сделать проверку для -o и -p, чтобы после них шел реальный путь/префикс
                case "-o" -> optionsBuilder.setResultPath(args[++i]);
                case "-p" -> optionsBuilder.setPrefix(args[++i]);
            }
        }

        return optionsBuilder;
    }

}
