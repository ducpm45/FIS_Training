package com.fis.sprint_4.repository;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.model.CriminalCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CriminalCaseRepoTest {
    @Autowired
    private CriminalCaseRepo criminalCaseRepo;
    @Test
    void testCreateReadUpdateDelete() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setDetailDescription("CASE001");
        log.info("Saved case: {}", criminalCaseRepo.save(criminalCase));
        Iterable<CriminalCase> criminalCases = criminalCaseRepo.findAll();
        criminalCases.forEach(criminal -> log.info("Case in list: {}", criminal));
        assertThat(criminalCases).extracting(CriminalCase::getDetailDescription).contains("CASE001");
        criminalCase.setDetailDescription("CASE002");
        log.info("Updated case: {}", criminalCaseRepo.save(criminalCase));
        assertThat("CASE002".equals(criminalCaseRepo.findById(1L).orElseThrow().getDetailDescription()));
        criminalCaseRepo.deleteAll();
        assertThat(criminalCaseRepo.findAll()).isEmpty();
    }
    @Test
    void testFindByCaseStatus() {
        CriminalCase criminalCase1 = new CriminalCase();
        criminalCase1.setCaseStatus(CaseStatus.SUBMITTED);
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseStatus(CaseStatus.SUBMITTED);
        criminalCaseRepo.save(criminalCase1);
        criminalCaseRepo.save(criminalCase2);
        criminalCaseRepo.findByCaseStatus(CaseStatus.SUBMITTED)
                .forEach(criminalCase -> log.info("Case by status: {}", criminalCase));
    }
    @Test
    void findByCaseNumber() {
        log.info("Case: {}", criminalCaseRepo.findByCaseNumber("CASE10"));
    }

    @Test
    void findCriminalCasesByDetectiveId() {
        criminalCaseRepo.findCriminalCasesByDetectiveId(1L).forEach(criminalCase -> log.info("Case: {}", criminalCase));
    }
}