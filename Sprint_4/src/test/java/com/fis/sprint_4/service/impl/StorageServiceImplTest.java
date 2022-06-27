package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.Storage;
import com.fis.sprint_4.repository.StorageRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class StorageServiceImplTest {
    private StorageServiceImpl storageService;
    private StorageRepo storageRepo;
    private Storage storage;


    @BeforeEach
    public void init() {
        storageRepo = mock(StorageRepo.class);
        storageService = new StorageServiceImpl(storageRepo);
        storage = new Storage();
        storage.setId(1L);
        storage.setLocation("KHO1");
        storage.setStorageName("STORAGE");
    }
    @Test
    void create() {
        when(storageRepo.save(storage)).thenReturn(storage);
        Storage savedStorage = storageService.create(storage);
        log.info("Created storage: {}", storage);
        assertThat(savedStorage).isNotNull();
        verify(storageRepo, times(1)).save(storage);
    }

    @Test
    void getAll() {
        List<Storage> storageList = new ArrayList<>();
        Storage storage1 = new Storage();
        Storage storage2 = new Storage();
        Storage storage3 = new Storage();
        storageList.add(storage1);
        storageList.add(storage2);
        storageList.add(storage3);

        when(storageRepo.findAll()).thenReturn(storageList);

        List<Storage> storages = storageService.getAll();
        storages.forEach(sto -> log.info("Storage in list: {}", sto));
        assertEquals(3, storages.size());
        verify(storageRepo, times(1)).findAll();
    }

    @Test
    void update() {
        when(storageRepo.save(storage)).thenReturn(storage);
        log.info("Update before: {}", storage);
        storage.setStorageName("STO001");
        storage.setLocation("KHO2");
        Storage updatedStorage = storageService.update(storage);
        log.info("Updated storage: {}", updatedStorage);
        assertThat("STO001".equals(updatedStorage.getStorageName()));
        assertThat("KHO2".equals(updatedStorage.getLocation()));
    }

    @Test
    void findById() {
        when(storageRepo.findById(1L)).thenReturn(Optional.of(storage));
        Storage savedStorage = storageService.findById(storage.getId());
        log.info("Find by id storage: {}", savedStorage);
        assertThat(savedStorage).isNotNull();
    }

    @Test
    void delete() {
        storageService.delete(storage.getId());
        verify(storageRepo, times(1)).deleteById(storage.getId());
    }
}