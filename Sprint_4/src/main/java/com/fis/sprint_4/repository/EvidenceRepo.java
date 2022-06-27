package com.fis.sprint_4.repository;

import com.fis.sprint_4.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvidenceRepo extends JpaRepository<Evidence, Long>, EvidenceRepoCustom {
    List<Evidence> getEvidencesByCriminalCase_CaseNumber(String caseNumber);
}
