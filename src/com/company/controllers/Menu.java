package com.company.controllers;

import com.company.view.CountTasks;
import com.company.view.Display;


import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public void run() throws IOException {
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
                        new CreateTask().userInput();
                        break;
                    case 3:
                        new EditTask();
                        break;
                    case 4:
                        System.out.println("\033[0;35m" + "Saving and Quitting..." + "\033[0m");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Invalid input");
                }

            } catch (Exception e) {
                System.err.println("invalid input");
            }
        } while (true);
    }

    public static void printOptions() throws IOException {
        System.out.println("\033[1;33m" + "Welcome to " + "\033[1;32m" + "To" + "\033[1;34m" + "Do" + "\033[1;35m" + "Ly" + "\u001B[33m");
        System.out.printf(
                "You have %s%s%s tasks todo and %s%s%s tasks are done!\n",
                "\u001B[36m",
                new CountTasks().getToDoTasksCount(),
                "\u001B[33m",
                "\u001B[36m",
                new CountTasks().getDoneTasksCount(),
                "\u001B[33m"
        );
        System.out.println("Pick an option: ");
        System.out.println("\u001B[36m" + "\t (1)" + "\u001B[33m" + " Show Task List (by date or project)");
        System.out.println("\u001B[36m" + "\t (2)" + "\u001B[33m" + " Add New Task");
        System.out.println("\u001B[36m" + "\t (3)" + "\u001B[33m" + " Edit Task (update, mark as done, remove)");
        System.out.println("\u001B[36m" + "\t (4)" + "\u001B[33m" + " Save and Quit");
        System.out.print("> " + "\u001B[0m");

    }
}
