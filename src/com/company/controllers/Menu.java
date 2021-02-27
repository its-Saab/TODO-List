package com.company.controllers;

import java.util.Scanner;

public class Menu {

    public void run() {
        Scanner scanner;
        int choosenOption;
        do {
            printOptions();
            scanner = new Scanner(System.in);
            try {
                choosenOption = scanner.nextInt();
                switch (choosenOption) {
                    case 1:
                        System.out.println("Show Task List");
                        break;
                    case 2:
                        System.out.println("Add New Task");
                        break;
                    case 3:
                        System.out.println("Edit Task");
                        break;
                    case 4:
                        System.out.println("Save and Quit");
                        scanner.close();
                        System.exit(0);
                        break;
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
