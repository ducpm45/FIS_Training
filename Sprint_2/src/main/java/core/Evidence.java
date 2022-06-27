package core;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Evidence {
    private long id;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private CriminalCase criminalCase;
    private Storage storage;
    private String number;
    private String itemName;
    private String notes;
    private boolean archived;
    private Set<TrackEntry> trackEntrySet;

    /**
     * Builder class
     */
    public static class Builder{
        private long id = 1;
        private int version = 1;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime modifiedAt = LocalDateTime.now();
        private CriminalCase criminalCase = null;
        private Storage storage = null;
        private String number;
        private String itemName;
        private String notes;
        private boolean archived = false;
        private Set<TrackEntry> trackEntrySet = null;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Builder setCriminalCase(CriminalCase criminalCase) {
            this.criminalCase = criminalCase;
            return this;
        }

        public Builder setStorage(Storage storage) {
            this.storage = storage;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setArchived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public Builder setTrackEntrySet(Set<TrackEntry> trackEntrySet) {
            this.trackEntrySet = trackEntrySet;
            return this;
        }

        public Evidence build() {
            return new Evidence(this);
        }
    }
    private Evidence () {
        super();
    }

    private Evidence (Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        modifiedAt = builder.modifiedAt;
        criminalCase = builder.criminalCase;
        storage = builder.storage;
        number = builder.number;
        itemName = builder.itemName;
        notes = builder.notes;
        archived = builder.archived;
        trackEntrySet = builder.trackEntrySet;
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getNumber() {
        return number;
    }

    public String getItemName() {
        return itemName;
    }

    public String getNotes() {
        return notes;
    }

    public boolean getArchived() {
        return archived;
    }

    public Set<TrackEntry> getTrackEntrySet() {
        return trackEntrySet;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", criminalCase=" + criminalCase +
                ", storage=" + storage +
                ", number='" + number + '\'' +
                ", itemName='" + itemName + '\'' +
                ", notes='" + notes + '\'' +
                ", archived=" + archived +
                ", trackEntrySet=" + trackEntrySet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evidence evidence = (Evidence) o;
        return id == evidence.id
                && version == evidence.version
                && archived == evidence.archived
                && Objects.equals(createdAt, evidence.createdAt)
                && Objects.equals(modifiedAt, evidence.modifiedAt)
                && Objects.equals(criminalCase, evidence.criminalCase)
                && Objects.equals(storage, evidence.storage)
                && Objects.equals(number, evidence.number)
                && Objects.equals(itemName, evidence.itemName)
                && Objects.equals(notes, evidence.notes)
                && Objects.equals(trackEntrySet, evidence.trackEntrySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, createdAt, modifiedAt, criminalCase, storage, number, itemName, notes, archived, trackEntrySet);
    }
}
