package com.fis.sprint_3.repository;

import com.fis.sprint_3.model.AbstracEntity;
import com.fis.sprint_3.model.Detective;

import java.util.Set;

public interface AbstractRepo<T extends AbstracEntity> {
    void create(T obj);

    Set<Detective> getALl();

    T findById(Long id);

    T update(T obj);

    void deleteById(Long id);
}
