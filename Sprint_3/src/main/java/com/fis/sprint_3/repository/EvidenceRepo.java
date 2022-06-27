package com.fis.sprint_3.repository;

import com.fis.sprint_3.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepo extends JpaRepository<Evidence, Long> {

}
