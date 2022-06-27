package fis.core;

import java.time.LocalDateTime;
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
        private long id = 1L;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime modifiedAt = LocalDateTime.now();
        private CriminalCase criminalCase = null;
        private Storage storage = null;
        private String number;
        private String itemName;
        private String notes;
        private boolean archived;
        private Set<TrackEntry> trackEntrySet = null;

        public Builder setId(long id) {
            this.id = id;
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

    /**
     * get id value
     * @return evidence id
     */

    public long getId() {
        return id;
    }

    /**
     * get evidence creation date
     * @return date
     */

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * get evidence modify date
     * @return date
     */

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * get the case to which this evidence relates
     * @return criminal case
     */
    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    /**
     * get the storage in which this evidence is saving
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * get evidence number
     * @return evidence number
     */
    public String getNumber() {
        return number;
    }

    /**
     * get item name
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * get evidence notes
     * @return evidence notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * get storage status
     * @return true -> if this evidence has been stored, false -> if this evidence not yet archived
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * get track entry
     * @return HashSet<TrackEntry>
     */
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
}
