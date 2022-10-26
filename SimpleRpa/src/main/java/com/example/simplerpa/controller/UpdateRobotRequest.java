package com.example.simplerpa.controller;

import java.time.LocalDateTime;

public record UpdateRobotRequest(int robotId, String robotName, String ip, int portNum, String user, String password) {
}