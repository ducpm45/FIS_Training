package com.fis.sprint_3.service.impl;

import com.fis.sprint_3.model.CriminalCase;
import com.fis.sprint_3.repository.CriminalCaseRepo;
import com.fis.sprint_3.service.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalCaseServiceImpl implements CriminalCaseService {
    @Autowired
    private CriminalCaseRepo criminalCaseRepo;
    @Override
    public void create(CriminalCase criminalCase) {
        criminalCaseRepo.save(criminalCase);
    }

    @Override
    public List<CriminalCase> getAll() {
        return criminalCaseRepo.findAll();
    }

    @Override
    public CriminalCase update(CriminalCase criminalCase) {
        return criminalCaseRepo.save(criminalCase);
    }

    @Override
    public void deleteById(Long id) {
        criminalCaseRepo.deleteById(id);
    }

    @Override
    public CriminalCase findById(Long id) {
        return criminalCaseRepo.findById(id).orElse(null);
    }
}
