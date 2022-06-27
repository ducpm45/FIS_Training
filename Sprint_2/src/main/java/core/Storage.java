package core;

import java.time.LocalDateTime;
import java.util.Set;

public class Storage {
    private long id;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;
    private String location;
    private Set<Evidence> evidencesSet;

    /**
     * Builder class
     */
    public static class Builder {
        private long id;
        private int version;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime modifiedAt = LocalDateTime.now();
        private String name;
        private String location;
        private Set<Evidence> evidencesSet = null;

        public Storage.Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Storage.Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public Storage.Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Storage.Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Storage.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Storage.Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Storage.Builder setEvidencesSet(Set<Evidence> evidencesSet) {
            this.evidencesSet = evidencesSet;
            return this;
        }

        public Storage build() {
            return new Storage(this);
        }
    }

    private Storage() {
        super();
    }

    private Storage(Builder builder) {
        id = builder.id;
        version = builder.version;
        createdAt = builder.createdAt;
        modifiedAt = builder.modifiedAt;
        name = builder.name;
        location = builder.location;
        evidencesSet = builder.evidencesSet;
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
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public Set<Evidence> getEvidencesSet() {
        return evidencesSet;
    }

}
