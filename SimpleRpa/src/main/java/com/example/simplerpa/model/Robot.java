package com.example.simplerpa.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class Robot {
    private final int robotId;
    private String robotName;
    private String ip;
    private int portNum;
    private String user;
    private String password;
    private boolean running;
    private boolean deleted;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Robot(String robotName, String ip, int portNum, String user, String password) {
        this.robotId = 0;
        this.robotName = robotName;
        this.ip = ip;
        this.portNum = portNum;
        this.user = user;
        this.password = password;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public Robot(int robotId, String robotName, String ip, int portNum, String user, String password, boolean running, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.robotId = robotId;
        this.robotName = robotName;
        this.ip = ip;
        this.portNum = portNum;
        this.user = user;
        this.password = password;
        this.running = running;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setIp(String ip) {
        this.ip = ip;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setPortNum(int portNum) {
        this.portNum = portNum;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setUser(String user) {
        this.user = user;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setPassword(String password) {
        this.password = password;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setRunning(boolean running) {
        this.running = running;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

}