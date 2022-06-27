package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.model.CriminalCase;
import com.fis.sprint_4.repository.CriminalCaseRepo;
import com.fis.sprint_4.service.CriminalCaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalCaseServiceImpl implements CriminalCaseService {
    private final CriminalCaseRepo criminalCaseRepo;

    public CriminalCaseServiceImpl(CriminalCaseRepo criminalCaseRepo) {
        this.criminalCaseRepo = criminalCaseRepo;
    }

    @Override
    public CriminalCase create(CriminalCase criminalCase) {
        return criminalCaseRepo.save(criminalCase);
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
    public CriminalCase findById(Long id) {
        return criminalCaseRepo.findById(id).orElseThrow();
    }

    @Override
    public List<CriminalCase> getCaseByStatus(CaseStatus caseStatus) {
        return criminalCaseRepo.findByCaseStatus(caseStatus);
    }

    @Override
    public void delete(Long id) {
        criminalCaseRepo.deleteById(id);
    }

    @Override
    public CriminalCase getCaseByCaseNumber(String caseNumber) {
        return criminalCaseRepo.findByCaseNumber(caseNumber);
    }

    @Override
    public List<CriminalCase> findCriminalCasesByDetectiveId(Long detectiveId) {
        return criminalCaseRepo.findCriminalCasesByDetectiveId(detectiveId);
    }
}
