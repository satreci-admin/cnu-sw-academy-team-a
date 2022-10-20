package com.example.simplerpa.model.Robot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RobotAddress {
    private String ip;
    private int portNum;

    public RobotAddress(String ip, int portNum) {
        this.ip = ip;
        this.portNum = portNum;
    }
}
