package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.core.TrackAction;
import com.fis.sprint_4.model.TrackEntry;
import com.fis.sprint_4.repository.TrackEntryRepo;
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
class TrackEntryServiceImplTest {
    private TrackEntryServiceImpl trackEntryServiceImpl;
    private TrackEntryRepo trackEntryRepo;
    private TrackEntry trackEntry;


    @BeforeEach
    public void init() {
        trackEntryRepo = mock(TrackEntryRepo.class);
        trackEntryServiceImpl = new TrackEntryServiceImpl(trackEntryRepo);
        trackEntry = new TrackEntry();
        trackEntry.setId(1L);
        trackEntry.setTrackAction(TrackAction.RETRIEVED);
        trackEntry.setReason("REASON");
    }
    @Test
    void create() {
        when(trackEntryRepo.save(trackEntry)).thenReturn(trackEntry);
        TrackEntry savedTrackEntry = trackEntryServiceImpl.create(trackEntry);
        log.info("Created trackentry: {}", savedTrackEntry);
        assertThat(savedTrackEntry).isNotNull();
        verify(trackEntryRepo, times(1)).save(trackEntry);
    }

    @Test
    void getAll() {
        List<TrackEntry> trackEntryList = new ArrayList<>();
        TrackEntry trackEntry1 = new TrackEntry();
        TrackEntry trackEntry2 = new TrackEntry();
        TrackEntry trackEntry3 = new TrackEntry();
        trackEntryList.add(trackEntry1);
        trackEntryList.add(trackEntry2);
        trackEntryList.add(trackEntry3);

        when(trackEntryRepo.findAll()).thenReturn(trackEntryList);

        List<TrackEntry> trackEntries = trackEntryServiceImpl.getAll();
        trackEntries.forEach(track -> log.info("Track entry in list: ", track));
        assertEquals(3, trackEntries.size());
        verify(trackEntryRepo, times(1)).findAll();
    }

    @Test
    void update() {
        when(trackEntryRepo.save(trackEntry)).thenReturn(trackEntry);
        log.info("Update before: {}", trackEntry);
        trackEntry.setTrackAction(TrackAction.RETURNED);
        trackEntry.setReason("Reason1");
        TrackEntry updatedTrackEntry = trackEntryServiceImpl.update(trackEntry);
        log.info("Updated track entry: {}", updatedTrackEntry);
        assertThat("Reason1".equals(updatedTrackEntry.getReason()));
        assertThat(TrackAction.RETURNED.equals(updatedTrackEntry.getTrackAction()));
    }

    @Test
    void findById() {
        when(trackEntryRepo.findById(1L)).thenReturn(Optional.of(trackEntry));
        TrackEntry savedTrackEntry = trackEntryServiceImpl.findById(trackEntry.getId());
        log.info("Find by id track entry: {}", savedTrackEntry);
        assertThat(savedTrackEntry).isNotNull();
    }

    @Test
    void delete() {
        trackEntryServiceImpl.delete(trackEntry.getId());
        verify(trackEntryRepo, times(1)).deleteById(trackEntry.getId());
    }
}