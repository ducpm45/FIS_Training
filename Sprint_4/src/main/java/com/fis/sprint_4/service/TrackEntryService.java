package com.fis.sprint_4.service;

import com.fis.sprint_4.model.TrackEntry;

import java.util.List;

public interface TrackEntryService {
    TrackEntry create(TrackEntry trackEntry);
    List<TrackEntry> getAll();
    TrackEntry update(TrackEntry trackEntry);
    TrackEntry findById(Long id);
    void delete(Long id);
}
