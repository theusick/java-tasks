package ru.mail.polis.homework.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Directories {


    /**
     * Реализовать рекурсивное удаление всех файлов и директорий из директории по заданному пути.
     * Метод должен возвращать количество удаленных файлов и директорий.
     * Если директории по существующему пути нет, то возвращаем 0.
     * Написать двумя способами. С использованием File
     * 2 тугрика
     */
    public static int removeWithFile(String path) {
        int deletionCounter = 0;

        File directory = new File(path);
        if (!directory.exists()) {
            return deletionCounter;
        }

        if (directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                deletionCounter += removeWithFile(file.toString());
            }
        }
        if (directory.delete()) {
            deletionCounter++;
        }
        return deletionCounter;
    }

    /**
     * С использованием Path
     * 2 тугрика
     */
    public static int removeWithPath(String path) throws IOException {
        int deletionCounter = 0;

        Path directory = Paths.get(path);
        if (Files.notExists(directory)) {
            return deletionCounter;
        }

        if (Files.isDirectory(directory)) {
            try (Stream<Path> pathStream = Files.list(directory)) {
                List<Path> directoryPaths = pathStream.collect(Collectors.toList());
                for (Path directoryPath : directoryPaths) {
                    deletionCounter += removeWithPath(directoryPath.toString());
                }
            }
        }
        Files.delete(directory);
        deletionCounter++;
        return deletionCounter;
    }

}
