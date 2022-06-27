package com.fis.sprint_4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "storages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Storage extends AbstractEntity {

    @Column(name = "storage_name", unique = true)
    private String storageName;
    @Column(name = "location")
    private String location;
    @OneToMany(mappedBy = "storage")
    private Set<Evidence> evidenceSet;

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + getId() +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                ", version=" + getVersion() +
                ", storageName='" + storageName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Storage storage = (Storage) o;

        if (!Objects.equals(storageName, storage.storageName)) return false;
        return Objects.equals(location, storage.location);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (storageName != null ? storageName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
