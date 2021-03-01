package com.company.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateTask {

    public CreateTask() {
        Scanner scanner = new Scanner(System.in);
        String regex = "^20[0-9][0-9]-([1][0-2]|[0][1-9])-([0-2][0-9]|[3][0-1])";
        String date;
        File currentDirectory;
        String projectName;
        String title;
        String description;

        System.out.print("Enter Project Name > ");
        projectName = scanner.nextLine().replaceAll("\\s", "-") + ".txt";
        currentDirectory = new File(projectName);
        if (currentDirectory.exists()) {
            System.out.printf("Project %s already exists\n ", projectName);
        } else {
            try (BufferedWriter newTask = new BufferedWriter(new FileWriter(projectName))) {

                System.out.print("Enter Task title > ");
                title = scanner.nextLine();
                System.out.print("Enter Task Description > ");
                description = scanner.nextLine();
                do {
                    System.out.print("Enter Task due date yyyy-MM-dd > ");
                    date = scanner.nextLine();
                    if (!date.matches(regex)) {
                        System.out.println("Invalid date format");
                    }
                } while (!date.matches(regex));

                newTask.write(new Task(title, description, date).toString());
                System.out.println("Task added successfully!");
            } catch (IOException e) {
                System.out.println("Cannot write file: " + projectName);
            }

        }
    }

}
