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
import java.util.Scanner;
import java.util.stream.Collectors;

public class UpdateFile {
    private final String fileName;
    private final List<String> words;

    public UpdateFile(String fileName) throws IOException {
        this.fileName = fileName;
        this.words = Files
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

    public void updateTask(int chosenSection){
        Scanner scanner = new Scanner(System.in);
        switch (chosenSection) {
            case 1:
                System.out.printf("The current title is: %s%s%s\n", "\033[0;35m", words.get(1), "\033[0m");
                System.out.print("\033[1;33m" + "Enter new title > ");
                String updatedInput = scanner.nextLine();
                setState(1, updatedInput);
                break;
            case 2:
                System.out.printf("The current description is: %s%s%s\n", "\033[0;35m", words.get(3), "\033[0m");
                System.out.print("\033[1;33m" + "Enter new description > ");
                updatedInput = scanner.nextLine();
                setState(3, updatedInput);
                break;
            case 3:
                String regex = "^20[0-9][0-9]-([1][0-2]|[0][1-9])-([0-2][0-9]|[3][0-1])";
                System.out.printf("The current due date is: %s%s%s\n", "\033[0;35m", words.get(5), "\033[0m");
                do {
                    System.out.print("\033[1;33m" + "Enter new due date yyyy-mm-dd > ");
                    updatedInput = scanner.next();
                    if (!updatedInput.matches(regex)) {
                        System.out.println("Invalid date format");
                    } else {
                        setState(5, updatedInput);

                    }
                } while (!updatedInput.matches(regex));

                break;
            default:
                System.out.println("Invalid input");
        }

    }
}
