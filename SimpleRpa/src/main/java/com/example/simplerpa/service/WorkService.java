package com.example.simplerpa.service;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;

import java.util.List;

public interface WorkService {

    List<Work> getAllWork();
    //Optional<Work> findByCron(String schedulerCron);
    void createWork(Email email, String name, String contents, String schedulerCron);


}
