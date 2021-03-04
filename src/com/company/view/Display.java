package com.company.view;

import com.company.controllers.Menu;

import java.io.*;
import java.util.Scanner;

public class Display {
    Scanner scanner;
    public void displayTaskList() throws IOException {
        File currentDirectory = new File(".");
        System.out.println("\033[1;33m"+"How would you like to view Tasks?");
        System.out.print("1-By name / 2-By due date / 3- return > "+ "\033[0m");
        scanner = new Scanner(System.in);
        int sortMethod = scanner.nextInt();
        if (sortMethod == 1) {
            new Sort().sortByName(currentDirectory);
        } else if (sortMethod == 2) {
            new Sort().sortByDate(currentDirectory);
        } else {
            new Menu().run();
        }
    }
    //TODO add colors to the file lines
    public void displayTask() throws IOException {
        displayTaskList();
        scanner = new Scanner(System.in);
        System.out.print("\033[1;33m"+"Enter project name to display >"+"\033[0m");
        String projectName = scanner.next() + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(projectName))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.printf("\t%s%s\n","\033[1;34m", line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + projectName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + projectName);
        }
    }
}
