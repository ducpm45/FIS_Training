package com.fis.sprint_3.service.impl;

import com.fis.sprint_3.core.CaseStatus;
import com.fis.sprint_3.core.CaseType;
import com.fis.sprint_3.model.CriminalCase;
import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.repository.EvidenceRepo;
import com.fis.sprint_3.service.CriminalCaseService;
import com.fis.sprint_3.service.DetectiveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CriminalCaseServiceImplTest {
    @Autowired
    private CriminalCaseService criminalCaseService;
    @Autowired
    private DetectiveService detectiveService;
    @Autowired
    private EvidenceRepo evidenceRepo;

    @Test
    void create() {
        CriminalCase criminalCase = new CriminalCase();
        Detective detective = detectiveService.findById(2L);
        criminalCase.setLeadInvestigator(detective);
        criminalCaseService.create(criminalCase);

    }

    @Test
    void getAll() {
        criminalCaseService.getAll().forEach(System.out::println);
        Assertions.assertEquals(2, criminalCaseService.getAll().size());
    }

    @Test
    void update() {
        CriminalCase criminalCase = criminalCaseService.findById(3L);
        criminalCase.setCaseStatus(CaseStatus.SUBMITTED);
        criminalCase.setNumber("HS003");
        criminalCase.setCaseType(CaseType.INFRACTION);
        criminalCaseService.update(criminalCase);
        Assertions.assertEquals(CaseType.INFRACTION, criminalCaseService.findById(3L).getCaseType());
        Assertions.assertEquals(CaseStatus.SUBMITTED, criminalCaseService.findById(3L).getCaseStatus());
        Assertions.assertEquals("HS003", criminalCaseService.findById(3L).getNumber());
    }

    @Test
    void deleteById() {
        criminalCaseService.deleteById(3L);
        Assertions.assertEquals(1, criminalCaseService.getAll().size());
    }
}