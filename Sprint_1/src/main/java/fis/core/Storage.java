package fis.core;

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

    public Storage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evidence> getEvidencesSet() {
        return evidencesSet;
    }

    public void setEvidencesSet(Set<Evidence> evidencesSet) {
        this.evidencesSet = evidencesSet;
    }
}
