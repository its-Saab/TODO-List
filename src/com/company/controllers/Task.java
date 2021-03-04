package com.company.controllers;


public class Task {
    private final String title;
    private final String description;
    private final String dueDate;
    private boolean isDone = false;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task(String title, String description, String dueDate, boolean isDone) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }


    @Override
    public String toString() {
        return
                "Title: \n" + title + '\n' +
                "Description:\n" + description + '\n' +
                "Due date: \n" + dueDate + '\n' +
                "Status: \n" + (isDone ? "Done" : "Not Done yet")
                ;
    }
}
