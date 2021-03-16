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
                scanner.nextLine();
                switch (chosenSection) {
                    case 1:
                        System.out.printf("The current title is: %s%s%s\n", "\033[0;35m", words.get(1), "\033[0m");
                        System.out.print("\033[1;33m" + "Enter new title > ");
                        String updatedInput = scanner.nextLine();
                        new UpdateFile(fileName).setState(1, updatedInput);
                        break;
                    case 2:
                        System.out.printf("The current description is: %s%s%s\n", "\033[0;35m", words.get(3), "\033[0m");
                        System.out.print("\033[1;33m" + "Enter new description > ");
                        updatedInput = scanner.next();
                        new UpdateFile(fileName).setState(3, updatedInput);
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
                                new UpdateFile(fileName).setState(5, updatedInput);

                            }
                        } while (!updatedInput.matches(regex));

                        break;
                    default:
                        System.out.println("Invalid input");
                }
                break;
        }
    }
}
