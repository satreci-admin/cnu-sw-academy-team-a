package com.example.simplerpa.service;

import com.example.simplerpa.model.Robot;
import com.example.simplerpa.model.Work;
import com.example.simplerpa.repository.RobotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Robot updateRobot(int robotId, String robotName, String ip, int portNum, String user, String password) {
        var robot = new Robot(robotId,
                robotName,
                ip,
                portNum,
                user,
                password,
                robotRepository.findById(robotId).get().isRunning(),
                robotRepository.findById(robotId).get().isDeleted(),
                robotRepository.findById(robotId).get().getCreatedAt(),
                LocalDateTime.now());

        return robotRepository.update(robot);
    }


}
