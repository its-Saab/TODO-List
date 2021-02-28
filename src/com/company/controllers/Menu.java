package com.company.controllers;

import com.company.view.Display;
import java.util.Scanner;

public class Menu {

    public void run() {
        Scanner scanner;
        int chosenOption;
        do {
            printOptions();
            scanner = new Scanner(System.in);
            try {
                chosenOption = scanner.nextInt();
                switch (chosenOption) {
                    case 1:
                        new Display().displayTask();
                        break;
                    case 2:
                        new TaskOperations().createTask();
                        break;
                    case 3:
                        new TaskOperations().editTask();
                        break;
                    case 4:
                        System.out.println("Saving and Quitting");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Invalid input");
                }

            } catch (Exception e) {
                System.out.println("invalid input");
            }
        } while (true);
    }

    public static void printOptions() {
        System.out.println("Welcome to ToDoLy");
        System.out.println("You have X tasks todo and Y tasks are done!");
        System.out.println("Pick an option: ");
        System.out.println("\t 1- Show Task List (by date or project)");
        System.out.println("\t 2- Add New Task");
        System.out.println("\t 3- Edit Task (update, mark as done, remove)");
        System.out.println("\t 4- Save and Quit");
        System.out.print("> ");

    }
}
