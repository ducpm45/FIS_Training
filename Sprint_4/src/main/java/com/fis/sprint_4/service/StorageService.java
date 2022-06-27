package com.fis.sprint_4.service;

import com.fis.sprint_4.model.Storage;
import java.util.List;

public interface StorageService {
    Storage create(Storage storage);
    List<Storage> getAll();
    Storage update(Storage storage);
    Storage findById(Long id);
    void delete(Long id);
}
