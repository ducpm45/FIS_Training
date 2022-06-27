package com.fis.sprint_4.service;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.model.CriminalCase;

import java.util.List;

public interface CriminalCaseService {
    CriminalCase create(CriminalCase criminalCase);

    List<CriminalCase> getAll();

    CriminalCase update(CriminalCase criminalCase);

    CriminalCase findById(Long id);

    List<CriminalCase> getCaseByStatus(CaseStatus caseStatus);

    void delete(Long id);

    CriminalCase getCaseByCaseNumber(String caseNumber);
    List<CriminalCase> findCriminalCasesByDetectiveId(Long detectiveId);
}
