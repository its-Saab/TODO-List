package com.company.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateTask extends TaskOperations {
    Scanner scanner;
    public CreateTask() {
        System.out.print("Enter Project Name > ");
        scanner = new Scanner(System.in);
        String projectNameWithTimestamp = scanner.nextLine();
        String projectName = projectNameWithTimestamp.replaceAll("\\s", "-") + ".txt";
        File currentDirectory = new File(projectName);
        if (currentDirectory.exists()) {
            System.out.printf("Project %s already exists\n ", projectName);
        } else {
            try (BufferedWriter newTask = new BufferedWriter(new FileWriter(projectName))) {

                System.out.print("Enter Task title > ");
                String title = scanner.nextLine();
                System.out.print("Enter Task Description > ");
                String description = scanner.nextLine();
                System.out.print("Enter Task due date yyyy-MM-dd > ");
                String date = scanner.nextLine();
                newTask.write(new Task(title, description, date).toString());
                System.out.println("Task added successfully!");
            } catch (IOException e) {
                System.out.println("Cannot write file: " + projectName);
            }

        }
    }

}
