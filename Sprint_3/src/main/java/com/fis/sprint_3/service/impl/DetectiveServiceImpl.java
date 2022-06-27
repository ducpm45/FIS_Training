package com.fis.sprint_3.service.impl;

import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.repository.DetectiveRepo;
import com.fis.sprint_3.service.DetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class DetectiveServiceImpl implements DetectiveService {
    @Autowired
    @Qualifier("extraDetectiveRepoImpl")
    private DetectiveRepo detectiveRepo;
    @Override
    public void create(Detective detective) {
        detectiveRepo.create(detective);
    }

    @Override
    public Set<Detective> getALl() {
        return detectiveRepo.getALl();
    }

    @Override
    public Detective findById(Long id) {
        return detectiveRepo.findById(id);
    }

    @Override
    public Detective update(Detective detective) {
        return detectiveRepo.update(detective);
    }

    @Override
    public void deleteById(Long id) {
        detectiveRepo.deleteById(id);
    }
}
