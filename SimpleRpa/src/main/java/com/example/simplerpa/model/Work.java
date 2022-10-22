package com.example.simplerpa.model;

import lombok.Getter;

import java.time.LocalDateTime;
<<<<<<< Updated upstream
=======
import java.time.temporal.ChronoUnit;
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
    public void setContents(String contents){
        this.contents  = contents;
        this.updatedAt = LocalDateTime.now();
=======
    public Work(Email email, String name, String contents, String schedulerCron) {
        this.email = email;
        this.name = name;
        this.contents = contents;
        this.schedulerCron = schedulerCron;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        statementId = 0;
        deleted = false;
        isActive = 0;
    }

    public void setContents(String contents){
        this.contents  = contents;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
>>>>>>> Stashed changes
    }
    public void setIsActive(int isActive){
        this.isActive = isActive;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setSchedulerCron(String schedulerCron) {
        this.schedulerCron = schedulerCron;
<<<<<<< Updated upstream
        this.updatedAt = LocalDateTime.now();
=======
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
>>>>>>> Stashed changes
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
