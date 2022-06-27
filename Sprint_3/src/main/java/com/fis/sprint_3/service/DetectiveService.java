package com.fis.sprint_3.service;

import com.fis.sprint_3.model.Detective;

import java.util.Set;

public interface DetectiveService {
    void create(Detective detective);
    Set<Detective> getALl();
    Detective findById(Long id);
    Detective update(Detective detective);
    void deleteById(Long id);
}
