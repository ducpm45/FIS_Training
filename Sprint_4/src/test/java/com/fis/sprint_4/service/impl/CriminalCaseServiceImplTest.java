package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.core.CaseType;
import com.fis.sprint_4.model.CriminalCase;
import com.fis.sprint_4.repository.CriminalCaseRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CriminalCaseServiceImplTest {

    private CriminalCaseServiceImpl caseServiceImpl;
    private CriminalCaseRepo criminalCaseRepo;
    private CriminalCase criminalCase;

    @BeforeEach
    public void init() {
        criminalCaseRepo = mock(CriminalCaseRepo.class);
        caseServiceImpl = new CriminalCaseServiceImpl(criminalCaseRepo);
        criminalCase = new CriminalCase();
        criminalCase.setId(1L);
        criminalCase.setCaseNumber("CASE001");
        criminalCase.setCaseStatus(CaseStatus.IN_COURT);
        criminalCase.setCaseType(CaseType.FELONY);
        criminalCase.setNotes("Note");
    }

    @Test
    void create() {
        when(criminalCaseRepo.save(criminalCase)).thenReturn(criminalCase);
        CriminalCase savedCriminalCase = caseServiceImpl.create(criminalCase);
        log.info("Created case: {}", savedCriminalCase);
        assertThat(savedCriminalCase).isNotNull();
        verify(criminalCaseRepo, times(1)).save(criminalCase);
    }

    @Test
    void getAll() {
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        CriminalCase criminalCase1 = new CriminalCase();
        CriminalCase criminalCase2 = new CriminalCase();
        CriminalCase criminalCase3 = new CriminalCase();
        criminalCaseList.add(criminalCase1);
        criminalCaseList.add(criminalCase2);
        criminalCaseList.add(criminalCase3);

        when(criminalCaseRepo.findAll()).thenReturn(criminalCaseList);

        List<CriminalCase> criminalCases = caseServiceImpl.getAll();
        criminalCases.forEach(criminal -> log.info("Case in list: {}", criminal));
        assertEquals(3, criminalCases.size());
        verify(criminalCaseRepo, times(1)).findAll();
    }

    @Test
    void update() {
        log.info("Update before: {}", criminalCase);
        when(criminalCaseRepo.save(criminalCase)).thenReturn(criminalCase);
        criminalCase.setCaseNumber("CASE007");
        criminalCase.setCaseType(CaseType.MISDEMEANOR);
        CriminalCase updatedCriminalCase = caseServiceImpl.update(criminalCase);
        log.info("Updated case: {}", updatedCriminalCase);
        assertThat("CASE007".equals(updatedCriminalCase.getCaseNumber()));
        assertThat(CaseType.MISDEMEANOR.equals(updatedCriminalCase.getCaseType()));
    }

    @Test
    void findById() {
        when(criminalCaseRepo.findById(1L)).thenReturn(Optional.of(criminalCase));
        CriminalCase savedCriminalCase = caseServiceImpl.findById(criminalCase.getId());
        log.info("Case by id: {}", savedCriminalCase);
        assertThat(savedCriminalCase).isNotNull();
    }

    @Test
    void delete() {
        caseServiceImpl.delete(criminalCase.getId());
        verify(criminalCaseRepo, times(1)).deleteById(criminalCase.getId());
    }

    @Test
    void getCaseByStatus() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        CriminalCase criminalCase1 = new CriminalCase();
        criminalCase1.setCaseStatus(CaseStatus.SUBMITTED);
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseStatus(CaseStatus.SUBMITTED);
        criminalCases.add(criminalCase1);
        criminalCases.add(criminalCase2);
        when(criminalCaseRepo.findByCaseStatus(CaseStatus.SUBMITTED)).thenReturn(criminalCases);
        List<CriminalCase> criminalCaseList = caseServiceImpl.getCaseByStatus(CaseStatus.SUBMITTED);
        criminalCaseList.forEach(criminal -> log.info("Case by status: {}", criminal));
        assertEquals(2, criminalCaseList.size());
        verify(criminalCaseRepo, times(1)).findByCaseStatus(CaseStatus.SUBMITTED);
    }

    @Test
    void getCaseByCaseNumber() {
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseNumber("Case11");
        when(criminalCaseRepo.findByCaseNumber("Case11")).thenReturn(criminalCase2);
        log.info("Case: {}", caseServiceImpl.getCaseByCaseNumber("Case11"));
    }

    @Test
    void findCriminalCasesByDetectiveId() {
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        CriminalCase criminalCase1 = CriminalCase.builder()
                .caseNumber("Case1")
                .build();
        CriminalCase criminalCase2 = CriminalCase.builder()
                .caseNumber("case2")
                .build();
        criminalCaseList.add(criminalCase1);
        criminalCaseList.add(criminalCase2);
        when(criminalCaseRepo.findCriminalCasesByDetectiveId(1L)).thenReturn(criminalCaseList);
        caseServiceImpl.findCriminalCasesByDetectiveId(1L)
                .forEach(criminal -> log.info("Case: {}", criminal));
    }
}