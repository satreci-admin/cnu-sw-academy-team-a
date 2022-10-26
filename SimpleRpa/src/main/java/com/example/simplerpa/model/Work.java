package com.example.simplerpa.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class Work {
    private final Email email;
    private final int statementId;
    private final int robotId;
    private final String name;
    private String contents;
    private boolean deleted;
    private String schedulerCron;
    private int isActive;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Work(Email email, int statementId, int robotId, String name, String contents, boolean deleted, String schedulerCron, int isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.statementId = statementId;
        this.robotId = robotId;
        this.name = name;
        this.contents = contents;
        this.deleted = deleted;
        this.schedulerCron = schedulerCron;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Work(Email email, int robotId, String name, String contents, String schedulerCron) {
        this.email = email;
        this.robotId = robotId;
        this.name = name;
        this.contents = contents;
        this.schedulerCron = schedulerCron;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.statementId = 0;
        this.deleted = false;
        this.isActive = 0;
    }

    public void setContents(String contents) {
        this.contents = contents;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setIsActive(int isActive){
        this.isActive = isActive;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setSchedulerCron(String schedulerCron) {
        this.schedulerCron = schedulerCron;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

