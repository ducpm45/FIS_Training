package com.fis.sprint_4.repository;

import com.fis.sprint_4.model.Storage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorageRepoTest {
    @Autowired
    private StorageRepo storageRepo;

    @Test
    void testCreateReadDelete() {
        Storage storage = new Storage();
        storage.setStorageName("STORAGE001");
        storageRepo.save(storage);
        Iterable<Storage> detectives = storageRepo.findAll();
        Assertions.assertThat(detectives).extracting(Storage::getStorageName)
                .containsOnly("STORAGE001");
        storage.setLocation("KHU1");
        storageRepo.save(storage);
        Assertions.assertThat("KHU1".equals(storageRepo.findById(1L).orElseThrow().getLocation()));
        System.out.println(storageRepo.findById(1L));
        storageRepo.deleteAll();
        Assertions.assertThat(storageRepo.findAll()).isEmpty();
    }
}