package com.example.simplerpa.model.Robot;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class RobotDate {
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RobotDate() {
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
