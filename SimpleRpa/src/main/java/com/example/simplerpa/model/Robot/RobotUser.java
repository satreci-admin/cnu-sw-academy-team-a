package com.example.simplerpa.model.Robot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RobotUser {
    private String user;
    private String password;

    public RobotUser(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
