package com.example.simplerpa.service;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;

import java.util.List;

public interface WorkService {

    List<Work> getAllWork();

    Work createWork(Email email, String name, String contents, String schedulerCron);

}
