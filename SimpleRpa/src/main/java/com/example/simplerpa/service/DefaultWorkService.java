package com.example.simplerpa.service;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;
import com.example.simplerpa.repository.WorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultWorkService implements WorkService{

    private final WorkRepository workRepository;

    public DefaultWorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public List<Work> getAllWork() {
        return workRepository.findAll();
    }

    @Override
    public void createWork(Email email, String name, String contents, String schedulerCron) {
        var work = new Work(email, name, contents, schedulerCron);

        workRepository.insert(work);
    }






}
