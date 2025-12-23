package ru.rekklez;

import ru.rekklez.model.Options;
import ru.rekklez.util.DuplicateDescriptionOptionException;
import ru.rekklez.util.FileFilterUtil;

public class Main {
    static void main(String[] args) {

        try {
            Options options = FileFilterUtil.defineOptions(args);
            System.out.println(options);
        } catch (DuplicateDescriptionOptionException e) {
            System.out.println("Error: " + e.getMessage());
        }


        /*try(Stream<String> lines = Files.lines(Path.of("in1.txt"))) {
            //TODO: Далее Scanner'у скармливаем по строке и через него чекаем тип данных

            lines.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }

}
