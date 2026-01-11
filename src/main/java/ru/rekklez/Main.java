package ru.rekklez;

import ru.rekklez.model.Config;
import ru.rekklez.model.FileFilter;
import ru.rekklez.util.exception.*;
import ru.rekklez.util.Util;

import java.io.IOException;

public class Main {
    static void main(String[] args) {
        try {
            Config config = Util.parseArgs(args);
            FileFilter filter = new FileFilter(config);
            filter.run();
        }
        catch (NoArgsException | NoFilesException | IOException e) {
            System.err.println(e.getMessage());
            printUsage();
        }
    }

    private static void printUsage() {
        System.err.println("\nИспользование:");
        System.err.println("  java -jar file-filter.jar [опции] <файл1> <файл2> ...");
        System.err.println("\nОпции:");
        System.err.println("  -o <путь>     Путь для сохранения результатов (по умолчанию: текущая папка)");
        System.err.println("  -p <префикс>  Префикс для имен выходных файлов (по умолчанию: пусто)");
        System.err.println("  -a            Режим добавления в существующие файлы");
        System.err.println("  -s            Краткая статистика (только количество элементов)");
        System.err.println("  -f            Полная статистика (мин, макс, сумма, среднее)");
        System.err.println("\nПример:");
        System.err.println("  java -jar file-filter.jar -o ./output -p result_ -f input1.txt input2.txt");
    }
}
