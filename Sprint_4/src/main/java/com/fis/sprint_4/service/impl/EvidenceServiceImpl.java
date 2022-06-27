package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.Evidence;
import com.fis.sprint_4.repository.EvidenceRepo;
import com.fis.sprint_4.service.EvidenceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvidenceServiceImpl implements EvidenceService {
    private final EvidenceRepo evidenceRepo;

    public EvidenceServiceImpl(EvidenceRepo evidenceRepo) {
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    public Evidence create(Evidence evidence) {
        return evidenceRepo.save(evidence);
    }

    @Override
    public List<Evidence> getAll() {
        return evidenceRepo.findAll();
    }

    @Override
    public Evidence update(Evidence evidence) {
        return evidenceRepo.save(evidence);
    }

    @Override
    public Evidence findById(Long id) {
        return evidenceRepo.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        evidenceRepo.deleteById(id);
    }

    @Override
    public List<Evidence> getEvidencesByCriminalCase_CaseNumber(String caseNumber) {
        return evidenceRepo.getEvidencesByCriminalCase_CaseNumber(caseNumber);
    }

}
