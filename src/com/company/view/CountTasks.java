package com.company.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CountTasks {
    private int doneTaskscount;
    private int toDoTaskscount;

    public CountTasks() throws IOException {
        this.doneTaskscount = 0;
        this.toDoTaskscount = 0;
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
                                .collect(Collectors.toList());

                        if (eachTask.get(7).equals("Not Done yet")) {
                            toDoTaskscount++;
                        } else {
                            doneTaskscount++;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .collect(Collectors.toList());
    }

    public int getDoneTaskscount() {
        return doneTaskscount;
    }

    public int getToDoTaskscount() {
        return toDoTaskscount;
    }
}
