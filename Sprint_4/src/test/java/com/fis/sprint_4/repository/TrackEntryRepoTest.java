package com.fis.sprint_4.repository;

import com.fis.sprint_4.core.TrackAction;
import com.fis.sprint_4.model.TrackEntry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrackEntryRepoTest {
    @Autowired
    private TrackEntryRepo trackEntryRepo;

    @Test
    void testCreateReadUpdateDelete() {
        TrackEntry trackEntry = new TrackEntry();
        trackEntry.setReason("Track Entry");
        trackEntryRepo.save(trackEntry);
        Iterable<TrackEntry> trackEntries = trackEntryRepo.findAll();
        Assertions.assertThat(trackEntries).extracting(TrackEntry::getReason)
                .containsOnly("Track Entry");
        trackEntry.setTrackAction(TrackAction.RETRIEVED);
        trackEntryRepo.save(trackEntry);
        Assertions.assertThat(TrackAction.RETRIEVED.equals(trackEntryRepo.findById(1L).orElseThrow().getTrackAction()));

        trackEntryRepo.deleteAll();
        Assertions.assertThat(trackEntryRepo.findAll()).isEmpty();
    }
}