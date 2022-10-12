package com.example.simplerpa.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Work {
    private final Email email;
    private final int statementId;
    private final String name;
    private String contents;
    private boolean deleted;
    private String schedulerCron;
    private int isActive;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Work(Email email, int statementId, String name, String contents, boolean deleted, String schedulerCron, int isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.statementId = statementId;
        this.name = name;
        this.contents = contents;
        this.deleted = deleted;
        this.schedulerCron = schedulerCron;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setContents(String contents){
        this.contents  = contents;
        this.updatedAt = LocalDateTime.now();
    }
    public void setIsActive(int isActive){
        this.isActive = isActive;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setSchedulerCron(String schedulerCron) {
        this.schedulerCron = schedulerCron;
        this.updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
