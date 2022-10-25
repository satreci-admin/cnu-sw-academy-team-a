package com.example.simplerpa.controller;

public record CreateRobotRequest(String robotName, String ip, int portNum, String user, String password) {
}
