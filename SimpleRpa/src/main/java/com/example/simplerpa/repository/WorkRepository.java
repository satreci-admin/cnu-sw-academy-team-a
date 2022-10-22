package com.example.simplerpa.repository;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;

import java.util.*;

public interface WorkRepository {
<<<<<<< Updated upstream
    Work insert(Work work);
=======

    Work insert(Work work);

>>>>>>> Stashed changes
    Work update(Work work);
    List<Work> findAll();
    Optional<Work> findById(int workId);
    Optional<Work> findByName(String WorkName);
    Optional<Work> findByEmail(Email email);
<<<<<<< Updated upstream

    void deleteAll();
}
=======
    void deleteAll();
}
>>>>>>> Stashed changes
