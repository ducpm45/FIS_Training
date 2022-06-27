package com.fis.sprint_4.service;

import com.fis.sprint_4.model.Detective;

import java.util.List;

public interface DetectiveService {
    Detective create(Detective detective);
    List<Detective> getAll();
    Detective update(Detective detective);
    Detective findById(Long id);

    void delete(Long id);
    Detective findByUserName(String userName);
}
