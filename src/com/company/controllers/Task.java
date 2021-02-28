package com.company.controllers;

import java.time.LocalDateTime;
import java.util.Date;

public class Task {
    private String title;
    private String description;
    private String deadLine;
    private boolean isDone = false;

    public Task(String title, String description, String deadline) {
        this.title = title;
        this.description = description;
        this.deadLine = deadline;
    }

    public Task(String title, String description, String deadline, boolean isDone) {
        this.title = title;
        this.description = description;
        this.deadLine = deadline;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return
                "Title: \n" + title + '\n' +
                        "Description:\n" + description + '\n' +
                        "Dead Line: \n" + deadLine + '\n' +
                        "Status: \n" + (isDone ? "Done" : "Not Done yet")
                ;
    }
}
