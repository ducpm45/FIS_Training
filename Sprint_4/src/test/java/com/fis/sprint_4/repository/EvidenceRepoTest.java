package com.fis.sprint_4.repository;

import com.fis.sprint_4.model.Evidence;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EvidenceRepoTest {
    @Autowired
    private EvidenceRepo evidenceRepo;

    @Test
    void testCreateReadUpdateDelete() {
        Evidence evidence = new Evidence();
        evidence.setNotes("Evidence note");
        evidenceRepo.save(evidence);
        Iterable<Evidence> evidences = evidenceRepo.findAll();
        Assertions.assertThat(evidences).extracting(Evidence::getNotes)
                .containsOnly("Evidence note");
        evidence.setArchived(true);
        evidence.setNumber("EVIDENCE001");
        evidence.setItemName("Knife");
        evidenceRepo.save(evidence);
        Assertions.assertThat("Knife".equals(evidenceRepo.findById(1L).orElseThrow().getItemName()));

        evidenceRepo.deleteAll();
        Assertions.assertThat(evidenceRepo.findAll()).isEmpty();
    }

    @Test
    void getEvidencesByCriminalCase_CaseNumber() {
        evidenceRepo.getEvidencesByCriminalCase_CaseNumber("CASE10").forEach(evidence -> log.info("Evidence: {}", evidence));
    }
}