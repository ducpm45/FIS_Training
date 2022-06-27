package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.TrackEntry;
import com.fis.sprint_4.repository.TrackEntryRepo;
import com.fis.sprint_4.service.TrackEntryService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TrackEntryServiceImpl implements TrackEntryService {
    private final TrackEntryRepo trackEntryRepo;

    public TrackEntryServiceImpl(TrackEntryRepo trackEntryRepo) {
        this.trackEntryRepo = trackEntryRepo;
    }

    @Override
    public TrackEntry create(TrackEntry trackEntry) {
        return trackEntryRepo.save(trackEntry);
    }

    @Override
    public List<TrackEntry> getAll() {
        return trackEntryRepo.findAll();
    }

    @Override
    public TrackEntry update(TrackEntry trackEntry) {
        return trackEntryRepo.save(trackEntry);
    }

    @Override
    public TrackEntry findById(Long id) {
        return trackEntryRepo.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        trackEntryRepo.deleteById(id);
    }
}
