package com.company.view;

import java.io.*;
import java.util.Scanner;

public class Display {
    Scanner scanner;
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
