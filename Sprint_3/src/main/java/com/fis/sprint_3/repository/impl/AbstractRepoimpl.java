package com.fis.sprint_3.repository.impl;

import com.fis.sprint_3.core.NotImplementedException;
import com.fis.sprint_3.model.AbstracEntity;
import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.repository.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Set;

public class AbstractRepoimpl<T extends AbstracEntity> implements AbstractRepo<T> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public AbstractRepoimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(T obj) {
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public Set<Detective> getALl() {
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public T findById(Long id) {
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public T update(T obj) {
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public void deleteById(Long id) {
        throw new NotImplementedException("Not needed for this implementation.");
    }
}
