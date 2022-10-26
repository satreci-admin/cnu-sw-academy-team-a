package com.example.simplerpa.repository;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;

import java.util.List;
import java.util.Optional;

public class WorkStatementRepository implements WorkRepository {
    @Override
    public Work insert(Work work) {
        return null;
    }

    @Override
    public Work update(Work work) {
        return null;
    }

    @Override
    public List<Work> findAll() {
        return null;
    }

    @Override
    public Optional<Work> findById(int workId) {
        return Optional.empty();
    }

    @Override
    public Optional<Work> findByRobotId(int robotId) {
        return Optional.empty();
    }


    @Override
    public Optional<Work> findByName(String WorkName) {
        return Optional.empty();
    }

    @Override
    public Optional<Work> findByEmail(Email email) {
        return Optional.empty();
    }

    @Override
    public void deleteAll() {

    }
}
