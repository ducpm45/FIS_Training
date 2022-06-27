package com.fis.sprint_3.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "evidences")
public class Evidence extends AbstracEntity {
    @ManyToOne
    @JoinColumn(name="case_id", nullable = false)
    private CriminalCase criminalCase;
    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;
    @Column(name = "number", unique = true)
    private String number;
    @Column(name = "item")
    private String itemName;
    @Column(name = "notes")
    private String notes;
    @Column(name = "archived")
    private boolean archived;
    @OneToMany(mappedBy = "evidence")
    private Set<TrackEntry> trackEntrySet;

    @Override
    public String toString() {
        return "Evidence{" +
                "criminalCase=" + criminalCase +
                ", storage=" + storage +
                ", number='" + number + '\'' +
                ", itemName='" + itemName + '\'' +
                ", notes='" + notes + '\'' +
                ", archived=" + archived +
                ", trackEntrySet=" + trackEntrySet +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evidence evidence = (Evidence) o;
        return archived == evidence.archived && Objects.equals(criminalCase, evidence.criminalCase) && Objects.equals(storage, evidence.storage) && Objects.equals(number, evidence.number) && Objects.equals(itemName, evidence.itemName) && Objects.equals(notes, evidence.notes) && Objects.equals(trackEntrySet, evidence.trackEntrySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), criminalCase, storage, number, itemName, notes, archived, trackEntrySet);
    }

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase) {
        this.criminalCase = criminalCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Set<TrackEntry> getTrackEntrySet() {
        return trackEntrySet;
    }

    public void setTrackEntrySet(Set<TrackEntry> trackEntrySet) {
        this.trackEntrySet = trackEntrySet;
    }
}
