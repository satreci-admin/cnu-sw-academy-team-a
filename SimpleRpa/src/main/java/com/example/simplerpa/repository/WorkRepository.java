package com.example.simplerpa.repository;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;

import java.util.*;

public interface WorkRepository {
    Work insert(Work work);
    Work update(Work work);
    List<Work> findAll();
    Optional<Work> findById(int workId);
    Optional<Work> findByName(String WorkName);
    Optional<Work> findByEmail(Email email);

    void deleteAll();
}
