package com.company.controllers;

import java.util.Date;

public class Task {
    private String project;
    private String name;
    private Date deadLine;
    private boolean isDone = false;

    public Task(String project, String name, Date deadline) {
        this.project = project;
        this.name = name;
        this.deadLine = deadline;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
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
        return "Task{" +
                "project='" + project + '\'' +
                ", name='" + name + '\'' +
                ", deadLine=" + deadLine +
                ", isDone=" + isDone +
                '}';
    }
}
