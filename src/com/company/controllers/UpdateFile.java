package com.company.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateFile {
    private final String fileName;
    private final List<String> words;

    public UpdateFile(String fileName) throws IOException {
        this.fileName = fileName;
        this.words =  Files
                .lines(Paths.get(fileName))
                .map(s -> s.split("\\R"))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    public void setState(int index, String state) {

        words.set(index, state);
        Task updatedTask = new Task(words.get(1), words.get(3), words.get(5), words.get(7).equals("Done"));
        reWriteTask(updatedTask);
    }



    public void reWriteTask(Task updatedTask) {
        try (BufferedWriter newTask = new BufferedWriter(new FileWriter(fileName))) {
            newTask.write(updatedTask.toString());
            newTask.close();
            System.out.println("\033[0;35m" + "Task Updated successfully!" + "\033[0m");
        } catch (IOException e) {
            System.out.println("Cannot write file: " + fileName);
        }
    }
    public void deleteFile() {
        File deleteFile = new File(fileName);
        if (deleteFile.delete()) {
            System.out.println("\033[0;35m" + "Task deleted successfully" + "\033[0m");
        } else {
            System.out.println("Something went wrong" + "\033[0m");
        }
    }
}
