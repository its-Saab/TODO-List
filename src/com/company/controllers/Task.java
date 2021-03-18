package com.company.controllers;

/**
 * Task Class is the blueprint for the tasks
 * @author Mosaab Abbas
 * @since 1.0.0
 **/
public class Task {
    private final String title;
    private final String description;
    private final String dueDate;
    private boolean isDone = false;

    /**
     * Task's first constructor is to be used for the tasks that is created for the first time.
     **/
    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Task's second constructor is to be used for the updated tasks.
     **/
    public Task(String title, String description, String dueDate, boolean isDone) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    /**
     * toString method is designed to split each entry into a separate line so it can be indexed when reading/updating.
     **/
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
