package com.example.simplerpa.controller.api;

import com.example.simplerpa.model.Robot;
import com.example.simplerpa.service.RobotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RobotRestController {
    private final RobotService robotService;

    public RobotRestController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping("/api/v1/robots")
    public List<Robot> robotList(){
        return robotService.getAllRobots();
    }

}
