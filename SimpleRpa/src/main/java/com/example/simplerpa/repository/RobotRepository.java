package com.example.simplerpa.repository;

import com.example.simplerpa.model.Robot;

import java.util.List;
import java.util.Optional;

public interface RobotRepository {

    Robot insert(Robot robot);
    Robot update(Robot robot);

    List<Robot> findAll();
    Optional<Robot> findById(int robotId);

    Optional<Robot> findByName(String robotName);
    Optional<Robot> findByIp(String ip);
    Optional<Robot> findByUser(String user);
    Optional<Robot> findByRunning(Boolean running);

    Optional<Robot> deleteByName(String robotName);
    void deleteAll();

}