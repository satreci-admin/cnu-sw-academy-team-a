package com.example.simplerpa.controller;

import com.example.simplerpa.service.RobotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RobotController {
    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping("/robots")
    public String robotPage(Model model) {
        var robots = robotService.getAllRobots();
        model.addAttribute("robots", robots);
        return "robot-list";
    }

    @GetMapping("new-robot")
    public String newRobotPage(){
        return "new-robot";
    }

    @PostMapping("/robots")
    public String newRobot(CreateRobotRequest createRobotRequest) {
        robotService.createRobot(
            createRobotRequest.robotName(),
            createRobotRequest.ip(),
            createRobotRequest.portNum(),
            createRobotRequest.user(),
            createRobotRequest.password()
        );
        return "redirect:/robots";
    }
}
