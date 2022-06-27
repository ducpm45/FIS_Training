package com.fis.sprint_4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "evidences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evidence extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    private CriminalCase criminalCase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;
    @Column(name = "number", unique = true)
    private String number;
    @Column(name = "item_name")
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
                "id=" + getId() +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                ", version=" + getVersion() +
                ", number='" + number + '\'' +
                ", itemName='" + itemName + '\'' +
                ", notes='" + notes + '\'' +
                ", archived=" + archived +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Evidence evidence = (Evidence) o;

        if (archived != evidence.archived) return false;
        if (!Objects.equals(number, evidence.number)) return false;
        if (!Objects.equals(itemName, evidence.itemName)) return false;
        return Objects.equals(notes, evidence.notes);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (archived ? 1 : 0);
        return result;
    }
}
