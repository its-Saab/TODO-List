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


public class Sort {

    public void sortByName(File currentDirectory) throws IOException {

        Files.list(Paths.get(String.valueOf(currentDirectory)))
                .filter(x -> x.toString().endsWith(".txt"))
                .sorted()
                .map(Path::getFileName)
                .forEach(x -> System.out.println(x.toString()));
    }

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
        listOfFiles.forEach((x, y) -> System.out.println(x + " " + y.toString()));

    }


}
