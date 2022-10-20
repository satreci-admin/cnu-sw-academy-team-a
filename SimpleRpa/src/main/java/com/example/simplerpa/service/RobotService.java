package com.example.simplerpa.service;

import com.example.simplerpa.model.Robot.Robot;

import java.util.List;

public interface RobotService {

    List<Robot> getAllRobots();

    Robot createRobot(String robotName, String ip, int portNum, String user, String password);
}
