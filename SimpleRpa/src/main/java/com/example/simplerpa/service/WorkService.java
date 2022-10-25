package com.example.simplerpa.service;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;

import java.util.List;

public interface WorkService {

    List<Work> getAllWork();
    //Optional<Work> findByCron(String schedulerCron);
    Work createWork(Email email, String name, String contents, String schedulerCron);
    Work updateWork(int statementId, String name, String contents, String schedulerCron);


}
