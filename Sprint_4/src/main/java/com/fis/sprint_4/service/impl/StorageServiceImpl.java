package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.Storage;
import com.fis.sprint_4.repository.StorageRepo;
import com.fis.sprint_4.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepo storageRepo;

    public StorageServiceImpl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Override
    public Storage create(Storage storage) {
        return storageRepo.save(storage);
    }

    @Override
    public List<Storage> getAll() {
        return storageRepo.findAll();
    }

    @Override
    public Storage update(Storage storage) {
        return storageRepo.save(storage);
    }

    @Override
    public Storage findById(Long id) {
        return storageRepo.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        storageRepo.deleteById(id);
    }
}
