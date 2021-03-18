package com.company.view;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Sort Class is responsible for sorting the tasks in-order to display them in the right order (by date / by name)
 * @author Mosaab Abbas
 * @since 1.0.0
 **/

public class Sort {

    /**
     * sortByName method is designed to display the tasks sorted by alphabetical order
     * @param currentDirectory which is the directory where the files are saved.
     **/

    public void sortByName(File currentDirectory) throws IOException {

        Files.list(Paths.get(String.valueOf(currentDirectory)))
                .filter(x -> x.toString().endsWith(".txt"))
                .sorted()
                .map(Path::getFileName)
                .forEach(x -> System.out.printf("%s%15s%s\n", "\033[1;34m", x.toString().replaceAll(".txt", ""), "\033[0m"));
    }

    /**
     * sortByDate method is designed to display the tasks sorted by Due date.
     * @param currentDirectory which is the directory where the files are saved.
     **/
    public void sortByDate(File currentDirectory) throws IOException {
        TreeMap<String, Path> listOfFiles = new TreeMap<>();
        var filesInDirectory = Files.list(Paths.get(String.valueOf(currentDirectory)))
                .filter(x -> x.toString().endsWith(".txt"))
                .peek(x -> {
                    try {
                        var eachTask = Files
                                .lines(Paths.get(String.valueOf(x)))
                                .map(s -> s.split("\\R"))
                                .map(Arrays::asList)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());

                        listOfFiles.put(eachTask.get(5), x.getFileName());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .collect(Collectors.toList());
        listOfFiles.forEach((x, y) -> System.out.printf("%s%15s: %-5s%s\n", "\033[1;34m", x, y.toString().replaceAll(".txt", ""), "\033[0m"));
    }
}
