package com.company.view;

import java.io.*;
import java.util.Scanner;

public class Display {
    Scanner scanner;
    public void displayTaskList() throws IOException {
        File currentDirectory = new File(".");
        System.out.println("How would you like to view Tasks?");
        System.out.print("1-By name / 2-By due date > ");
        scanner = new Scanner(System.in);
        int sortMethod = scanner.nextInt();
        if(sortMethod == 1){
            new Sort().sortByName(currentDirectory);
        } else {
            new Sort().sortByDate(currentDirectory);
        }
    }

    public void displayTask() throws IOException {
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
