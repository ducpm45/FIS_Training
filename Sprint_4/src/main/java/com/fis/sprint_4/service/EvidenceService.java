package com.fis.sprint_4.service;

import com.fis.sprint_4.model.Evidence;

import java.util.List;

public interface EvidenceService {
    Evidence create(Evidence evidence);
    List<Evidence> getAll();
    Evidence update(Evidence evidence);
    Evidence findById(Long id);
    void delete(Long id);
    List<Evidence> getEvidencesByCriminalCase_CaseNumber(String caseNumber);
}
