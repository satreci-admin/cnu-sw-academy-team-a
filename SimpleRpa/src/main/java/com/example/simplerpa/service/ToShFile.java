package com.example.simplerpa.service;

import com.example.simplerpa.repository.WorkRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ToShFile {

    private final WorkRepository workRepository;

    public ToShFile(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public void toFile(int statementId){

        statementId = workRepository.findById(statementId).get().getStatementId();

        File file = new File("c:\\file\\statementId" + statementId + ".txt");

        FileOutputStream fileOutputStream;

        {
            try {
                fileOutputStream = new FileOutputStream(file, true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
