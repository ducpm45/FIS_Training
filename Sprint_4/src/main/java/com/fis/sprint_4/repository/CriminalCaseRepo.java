package com.fis.sprint_4.repository;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.model.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findByCaseStatus(CaseStatus caseStatus);
    CriminalCase findByCaseNumber(String caseNumber);

    @Query(value = "select * from criminal_case c join " +
            "(select cd.case_id case_id from case_detective cd " +
            "where detective_id = :detectiveId) temp where temp.case_id = c.id;", nativeQuery = true)
    List<CriminalCase> findCriminalCasesByDetectiveId(@Param("detectiveId") Long detectiveId);
}
