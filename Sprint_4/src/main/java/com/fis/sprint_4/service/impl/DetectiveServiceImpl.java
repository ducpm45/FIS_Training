package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.Detective;
import com.fis.sprint_4.repository.DetectiveRepo;
import com.fis.sprint_4.service.DetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class DetectiveServiceImpl implements DetectiveService {
    private final DetectiveRepo detectiveRepo;

    public DetectiveServiceImpl(DetectiveRepo detectiveRepo) {
        this.detectiveRepo = detectiveRepo;
    }

    @Override
    public Detective create(Detective detective) {
       return detectiveRepo.save(detective);
    }

    @Override
    public List<Detective> getAll() {
        return detectiveRepo.findAll();
    }

    @Override
    public Detective update(Detective detective) {
        log.info(detective.toString());
        return detectiveRepo.save(detective);
    }

    @Override
    public Detective findById(Long id) {
        return detectiveRepo.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        detectiveRepo.deleteById(id);
    }

    @Override
    public Detective findByUserName(String userName) {
        return detectiveRepo.findByUserName(userName);
    }
}
