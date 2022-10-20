package com.example.simplerpa.model.Robot;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Robot {

    private int robotId;
    private String robotName;
    private boolean running;
    private boolean deleted;

    private final RobotAddress robotAddress;
//    private String ip;
//    private int portNum;

    private final RobotUser robotUser;
//    private String user;
//    private String password;

    private final RobotDate robotDate;
//    private final LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

    public Robot(String robotName, String ip, int portNum, String user, String password) {
//        this.robotId = 0;
        this.robotName = robotName;
        this.robotAddress = new RobotAddress(ip, portNum);
//        this.ip = ip;
//        this.portNum = portNum;
        this.robotUser = new RobotUser(user, password);
//        this.user = user;
//        this.password = password;
        this.robotDate = new RobotDate();
//        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
//        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public Robot(int robotId, String robotName, String ip, int portNum, String user, String password, boolean running, boolean deleted) {
        this.robotId = robotId;
        this.robotName = robotName;
        this.running = running;
        this.deleted = deleted;
        this.robotAddress = new RobotAddress(ip, portNum);
        this.robotUser = new RobotUser(user, password);
        this.robotDate = new RobotDate();
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
        this.robotDate.setUpdatedAt();
//        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setIp(String ip) {
        this.robotAddress.setIp(ip);
//        this.ip = ip;
        this.robotDate.setUpdatedAt();
    }

    public void setPortNum(int portNum) {
        this.robotAddress.setPortNum(portNum);
//        this.portNum = portNum;
        this.robotDate.setUpdatedAt();
    }

    public void setUser(String user) {
        this.robotUser.setUser(user);
        this.robotDate.setUpdatedAt();
    }

    public void setPassword(String password) {
        this.robotUser.setPassword(password);
        this.robotDate.setUpdatedAt();
    }

    public void setRunning(boolean running) {
        this.running = running;
        this.robotDate.setUpdatedAt();
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        this.robotDate.setUpdatedAt();
    }

    public String getIp(){
        return robotAddress.getIp();
    }

    public int getPortNum() {
       return robotAddress.getPortNum();
    }

    public String getUser() {
        return robotUser.getUser();
    }

    public String getPassword() {
        return robotUser.getPassword();
    }

    public LocalDateTime getCreatedAt() {
        return robotDate.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt() {
        return robotDate.getUpdatedAt();
    }
}