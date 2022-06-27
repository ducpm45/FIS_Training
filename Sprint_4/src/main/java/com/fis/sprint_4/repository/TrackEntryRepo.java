package com.fis.sprint_4.repository;

import com.fis.sprint_4.model.TrackEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackEntryRepo extends JpaRepository<TrackEntry, Long> {
}
