package com.company.controllers;

import com.company.view.Display;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * EditTask Class is responsible for redirecting the user's request the UpdateFile class
 * with the required data to update the saved tasks
 * @author Mosaab Abbas
 * @since 1.0.0
 **/
public class EditTask {
    Scanner scanner;

    public EditTask() throws IOException {
        new Display().displayTaskList();
        System.out.print("\033[1;33m" + "Choose a Task to Edit > ");
        scanner = new Scanner(System.in);
        String fileName = scanner.nextLine() + ".txt";
        var words = Files.lines(Paths.get(fileName))
                .map(s -> s.split("\\R"))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.print("1-Mark as done, 2-Remove, 3-Update > ");
        int chosenOperation = scanner.nextInt();
        switch (chosenOperation) {

            case 1:
                System.out.printf("The current status is: %s%s%s\n", "\033[0;35m", words.get(7), "\033[0m");
                System.out.print("\033[1;33m" + "Mark as done? (y/n) > ");
                if (scanner.next().equals("y")) {
                    new UpdateFile(fileName).setState(7,"Done");
                } else {
                    new UpdateFile(fileName).setState(7,"Not done yet");
                }
                break;

            case 2:
                new UpdateFile(fileName).deleteFile();
                break;

            case 3:
                System.out.print("Choose a section to edit(1-Title, 2-Description, 3-Due date) >");
                int chosenSection = scanner.nextInt();
                new UpdateFile(fileName).updateTask(chosenSection);
                break;
        }
    }
}
