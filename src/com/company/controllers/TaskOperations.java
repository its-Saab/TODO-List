package com.company.controllers;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class TaskOperations {
    File task;
    FileWriter taskObj;
    Scanner scanner;

    public void createTask() {
        System.out.print("Enter Project Name > ");
        scanner = new Scanner(System.in);
        String projectName = scanner.nextLine().replaceAll("\\s", "-") + ".txt";
        File currentDirectory = new File(projectName);
        if (currentDirectory.exists()) {
            System.out.printf("Project %s already exists\n ", projectName);
        } else {
            try (BufferedWriter newTask = new BufferedWriter(new FileWriter(projectName))) {
                System.out.print("Enter Task title > ");
                String title = scanner.nextLine();
                System.out.print("Enter Task Description > ");
                String description = scanner.nextLine();
                System.out.print("Enter Task deadline yyyy-MM-dd > ");
                String date = scanner.nextLine();
                newTask.write(new Task(title, description, date).toString());
            } catch (IOException e) {
                System.out.println("Cannot write file: " + projectName);
            }
            System.out.println("Task added successfully!");

        }
    }

    public void editTask(){
        //TODO get the task and update the state
       /* displayTaskList();
        System.out.print("Choose a Task to edit > ");
        */

    }

    public void displayTaskList(){
        //TODO implement sorting both by date and project
        File currentDirectory = new File(".");

        for (String fileName : currentDirectory.list()) {
            if (fileName.endsWith(".txt")) {
                System.out.println(fileName);
            }
        }
    }

     public void displayTask(){
        displayTaskList();
        scanner = new Scanner(System.in);
        System.out.print("Enter project name to display >");
        String projectName = scanner.next() + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(projectName))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + projectName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + projectName);
        }
    }

}
