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
        System.out.print("Choose a Task to Edit > ");
        scanner = new Scanner(System.in);
        String fileName = scanner.nextLine() + ".txt";
        var words = Files
                .lines(Paths.get(fileName))
                .map(s -> s.split("\\R"))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.print("1-Mark as done, 2-Remove, 3-Update > ");
        int chosenOperation = scanner.nextInt();

        switch (chosenOperation) {
            //TODO fix duplicated code
            case 1:
                System.out.printf("The current status is: %s\n", words.get(7));
                System.out.print("Mark as done? (y/n) > ");
                if (scanner.next().equals("y")) {
                    words.set(7, "Done");
                } else {
                    words.set(7, "Not done yet");
                }
                System.out.printf("Status successfully changed  to: %s \n", words.get(7));
                try (BufferedWriter newTask = new BufferedWriter(new FileWriter(fileName))) {
                    newTask.write(new Task(words.get(1), words.get(3), words.get(5), words.get(7).equals("Done")).toString());
                } catch (IOException e) {
                    System.out.println("Cannot write file: " + fileName);
                }
                System.out.println("Task Updated successfully!");
                break;
            case 2:
                File deleteFile = new File(fileName);
                if (deleteFile.delete()) {
                    System.out.println("Task deleted successfully");
                } else {
                    System.out.println("Something went wrong");
                }
                break;
            case 3:
                System.out.println("Choose a section to edit(1-Title, 2-Description, 3-Due date)");
                int chosenSection = scanner.nextInt();
                scanner.useDelimiter("\n");
                switch (chosenSection) {
                    case 1:
                        System.out.printf("The current title is: %s\n", words.get(1));
                        System.out.print("Enter new title > ");
                        words.set(1, scanner.next());
                        System.out.printf("Title successfully changed to: %s \n", words.get(1));
                        break;
                    case 2:
                        System.out.printf("The current description is: %s\n", words.get(3));
                        System.out.print("Enter new description > ");
                        words.set(3, scanner.next());
                        System.out.printf("Description successfully changed to: %s \n", words.get(3));
                        break;
                    case 3:
                        String regex = "^20[0-9][0-9]-([1][0-2]|[0][1-9])-([0-2][0-9]|[3][0-1])";
                        String date;
                        System.out.printf("The current due date is: %s\n", words.get(5));
                        do{
                            System.out.print("Enter new due date yyyy-mm-dd > ");
                            date = scanner.next();
                            if(!date.matches(regex)) {
                                System.out.println("Invalid date format");
                            } else{
                                words.set(5, date);
                            }
                            }while(!date.matches(regex));
                        System.out.printf("Deadline successfully changed  to: %s \n", words.get(5));
                        break;
                    default:
                        System.out.println("Invalid input");
                }
                try (BufferedWriter newTask = new BufferedWriter(new FileWriter(fileName))) {
                    newTask.write(new Task(words.get(1), words.get(3), words.get(5), words.get(7).equals("Done")).toString());
                } catch (IOException e) {
                    System.out.println("Cannot write file: " + fileName);
                }
                System.out.println("Task Updated successfully!");
                break;
        }


    }
}
