package com.fis.sprint_4.model;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.core.CaseType;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "criminal_case")
public class CriminalCase extends AbstractEntity {

    @Column(name = "case_number", unique = true)
    private String caseNumber;
    @Column(name = "case_type")
    @Enumerated(EnumType.STRING)
    private CaseType caseType;
    @Column(name = "short_desc", length = 1000)
    private String shortDescription;
    @Column(name = "detail_desc", length = 5000)
    private String detailDescription;
    @Column(name = "case_status")
    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;
    @Column(name = "notes", length = 3000)
    private String notes;
    @OneToMany(mappedBy = "criminalCase", cascade = CascadeType.ALL)
    private Set<Evidence> evidenceSet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_detective_id")
    private Detective leadInvestigator;

    @ManyToMany
    @JoinTable(name = "case_detective",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id"))
    private Set<Detective> detectiveSet;

    @Override
    public String toString() {
        return "CriminalCase{" +
                "id=" + getId() +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                ", version=" + getVersion() +
                ", caseNumber='" + caseNumber + '\'' +
                ", caseType=" + caseType +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailDescription='" + detailDescription + '\'' +
                ", caseStatus=" + caseStatus +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriminalCase that = (CriminalCase) o;

        if (!Objects.equals(caseNumber, that.caseNumber)) return false;
        if (caseType != that.caseType) return false;
        if (!Objects.equals(shortDescription, that.shortDescription))
            return false;
        if (!Objects.equals(detailDescription, that.detailDescription))
            return false;
        if (caseStatus != that.caseStatus) return false;
        return Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        int result = caseNumber != null ? caseNumber.hashCode() : 0;
        result = 31 * result + (caseType != null ? caseType.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (detailDescription != null ? detailDescription.hashCode() : 0);
        result = 31 * result + (caseStatus != null ? caseStatus.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
