package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.Evidence;
import com.fis.sprint_4.repository.EvidenceRepo;
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
class EvidenceServiceImplTest {
    private EvidenceServiceImpl evidenceService;
    private EvidenceRepo evidenceRepo;
    private Evidence evidence;


    @BeforeEach
    public void init() {
        evidenceRepo = mock(EvidenceRepo.class);
        evidenceService = new EvidenceServiceImpl(evidenceRepo);
        evidence = new Evidence();
        evidence.setId(1L);
        evidence.setItemName("Knife");
        evidence.setArchived(false);
        evidence.setNumber("EVIDENCE1");
    }
    @Test
    void create() {
        when(evidenceRepo.save(evidence)).thenReturn(evidence);
        Evidence savedDetective = evidenceService.create(evidence);
        log.info("Created evidence: {}", savedDetective);
        assertThat(savedDetective).isNotNull();
        verify(evidenceRepo, times(1)).save(evidence);
    }

    @Test
    void getAll() {
        List<Evidence> detectiveList = new ArrayList<>();
        Evidence evidence1 = new Evidence();
        Evidence evidence2 = new Evidence();
        Evidence evidence3 = new Evidence();
        detectiveList.add(evidence1);
        detectiveList.add(evidence3);
        detectiveList.add(evidence2);

        when(evidenceRepo.findAll()).thenReturn(detectiveList);

        List<Evidence> evidences = evidenceService.getAll();
        evidences.forEach(evi -> log.info("Evidence in list: {}", evi));
        assertEquals(3, evidences.size());
        verify(evidenceRepo, times(1)).findAll();
    }

    @Test
    void update() {
        when(evidenceRepo.save(evidence)).thenReturn(evidence);
        log.info("Update before: {}", evidence);
        evidence.setNumber("EVIDENT111");
        evidence.setItemName("Gun");
        Evidence updatedEvidence = evidenceService.update(evidence);
        log.info("Updated evidence: {}", updatedEvidence);
        assertThat("EVIDENT1".equals(updatedEvidence.getNumber()));
        assertThat("Gun".equals(updatedEvidence.getItemName()));
    }

    @Test
    void findById() {
        when(evidenceRepo.findById(1L)).thenReturn(Optional.of(evidence));
        Evidence savedDetective = evidenceService.findById(evidence.getId());
        log.info("Find by id evidence: {}", savedDetective);
        assertThat(savedDetective).isNotNull();
    }

    @Test
    void delete() {
        evidenceService.delete(evidence.getId());
        verify(evidenceRepo, times(1)).deleteById(evidence.getId());
    }

}