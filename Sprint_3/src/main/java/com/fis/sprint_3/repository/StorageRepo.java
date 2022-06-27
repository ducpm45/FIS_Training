package com.fis.sprint_3.repository;

import com.fis.sprint_3.model.Storage;

import java.util.Optional;
import java.util.Set;

public interface StorageRepo extends AbstractRepo<Storage>{
    Optional<Storage> findByName(String name);

    Set<Storage> findByLocation(String location);
}
