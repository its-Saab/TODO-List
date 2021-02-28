package com.company.controllers;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskOperations {
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

    public void editTask() throws IOException {
        displayTaskList();
        System.out.print("Choose a Task to Edit > ");
        scanner = new Scanner(System.in);
        String fileName = scanner.nextLine() + ".txt";
        var words = Files
                .lines(Paths.get(fileName))
                .map(s -> s.split("\\R"))
                .map(a -> Arrays.asList(a))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());

        System.out.println("Choose a section to edit(1-Title, 2-Description, 3-Dead Line, 4-Status");
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
                System.out.printf("The current deadline is: %s\n", words.get(5));
                System.out.print("Enter new deadline yyyy-mm-dd > ");
                words.set(5, scanner.next());
                System.out.printf("Deadline successfully changed  to: %s \n", words.get(5));
                break;
            case 4:
                System.out.printf("The current deadline is: %s\n", words.get(7));
                System.out.print("Mark as done? (y/n) > ");
                if (scanner.next().equals("y")) {
                    words.set(7, "Done");
                } else {
                    words.set(7, "Not done yet");
                }
                System.out.printf("Status successfully changed  to: %s \n", words.get(7));
                break;
            default:
                System.out.println("Invalid input");
        }

        try (BufferedWriter newTask = new BufferedWriter(new FileWriter(fileName))) {
            newTask.write(new Task(words.get(1), words.get(3), words.get(5), words.get(7).equals("Done") ? true : false).toString());
        } catch (IOException e) {
            System.out.println("Cannot write file: " + fileName);
        }
        System.out.println("Task Updated successfully!");
    }

    public void displayTaskList() {
        //TODO implement sorting both by date and project
        File currentDirectory = new File(".");

        for (String fileName : currentDirectory.list()) {
            if (fileName.endsWith(".txt")) {
                System.out.println(fileName);
            }
        }
    }

    public void displayTask() {
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
