package com.example.simplerpa.service;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;
import com.example.simplerpa.repository.WorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Work createWork(Email email, int robotId, String name, String contents, String schedulerCron) {
        var work = new Work(email, robotId, name, contents, schedulerCron);

        return workRepository.insert(work);
    }

    public Work updateWork(int statementId, int robotId, String name, String contents, String schedulerCron) {

        var work = new Work(workRepository.findById(statementId).get().getEmail(),
                statementId,
                robotId,
                name,
                contents,
                workRepository.findById(statementId).get().isDeleted(),
                schedulerCron,
                workRepository.findById(statementId).get().getIsActive(),
                workRepository.findById(statementId).get().getCreatedAt(),
                LocalDateTime.now());

        return workRepository.update(work);

    }

}
