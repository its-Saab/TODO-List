package com.company.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * CreateTask Class receives create a task request from Menu class.
 * @author Mosaab Abbas
 * @since 1.0.0
 **/

public class CreateTask {

    /**
     * userInput method is designed to take the user input and forward the data to writeTask method.
     **/
    public void userInput() {

        Scanner scanner = new Scanner(System.in);
        String regex = "^20[0-9][0-9]-([1][0-2]|[0][1-9])-([0-2][0-9]|[3][0-1])";
        String date;
        String title;
        String description;

        System.out.print("\033[1;33m" + "Enter Project Name > ");
        String fileName = scanner.nextLine().replaceAll("\\s", "-") + ".txt";

        System.out.print("Enter Task title > ");
        title = scanner.nextLine();
        if (title.length() == 0) {
            title = "none";
        }
        System.out.print("Enter Task Description > ");
        description = scanner.nextLine();
        if (description.length() == 0) {
            description = "None";
        }
        do {
            System.out.print("Enter Task due date yyyy-MM-dd > ");
            date = scanner.nextLine();
            if (!date.matches(regex)) {
                System.out.println("\033[0;31m" + "Invalid date format" + "\033[1;33m");
            }
        } while (!date.matches(regex));

        Task task = new Task(title, description, date);

        writeTask(fileName, task);

    }

    /**
     * writeTask method is designed to write tasks to file.
     * @param fileName which is the desired file name for the new task.
     * @param task which is the task that the user wants to write to the file.
     **/
    public void writeTask(String fileName, Task task) {
        File currentDirectory = new File(fileName);
        if (currentDirectory.exists()) {
            System.out.printf("Project %s already exists\n ", fileName);
        } else {
            try (BufferedWriter newTask = new BufferedWriter(new FileWriter(fileName))) {

                newTask.write(task.toString());
                newTask.close();
                System.out.println("\033[0;35m" + "Task added successfully!" + "\033[0m");
            } catch (IOException e) {
                System.out.println("Cannot write file: " + fileName);
            }

        }
    }
}
