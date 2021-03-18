package com.company.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * CountTasks Class is responsible for reading the tasks files and count the Done/To do tasks
 * to be used in the Menu class.
 * @author Mosaab Abbas
 * @since 1.0.0
 **/

public class CountTasks {
    private int doneTasksCount;
    private int toDoTasksCount;

    /*
    * CountTasks method is designed to read each file and count the ones that are marked as Done/Not Done yet
    * in-order to be used in the Menu class
    * */

    public CountTasks() throws IOException {
        this.doneTasksCount = 0;
        this.toDoTasksCount = 0;
        File currentDirectory = new File(".");
        var filesInDirectory = Files.list(Paths.get(String.valueOf(currentDirectory)))
                .filter(x -> x.toString().endsWith(".txt"))
                .peek(x -> {
                    try {
                        var eachTask = Files
                                .lines(Paths.get(String.valueOf(x)))
                                .map(s -> s.split("\\R"))
                                .map(Arrays::asList)
                                .flatMap(Collection::stream)
                                .filter(file -> file.length() > 0)
                                .collect(Collectors.toList());

                        if (eachTask.size() != 0) {
                            if (eachTask.get(7).equals("Not Done yet")) {
                                toDoTasksCount++;
                            } else {
                                doneTasksCount++;
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .collect(Collectors.toList());
    }

    public int getDoneTasksCount() {
        return doneTasksCount;
    }

    public int getToDoTasksCount() {
        return toDoTasksCount;
    }
}
