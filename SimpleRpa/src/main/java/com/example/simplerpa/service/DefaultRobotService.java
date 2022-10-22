package com.example.simplerpa.service;

import com.example.simplerpa.model.Robot;
import com.example.simplerpa.repository.RobotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultRobotService implements RobotService {

    private final RobotRepository robotRepository;

    public DefaultRobotService(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }

    @Override
    public List<Robot> getAllRobots() {
        return robotRepository.findAll();
    }

    @Override
    public Robot createRobot(String robotName, String ip, int portNum, String user, String password) {
        var robot = new Robot(robotName, ip, portNum, user, password);
        return robotRepository.insert(robot);
    }


}
