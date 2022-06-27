package com.fis.sprint_3.model;

import com.fis.sprint_3.core.CaseStatus;
import com.fis.sprint_3.core.CaseType;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "criminal_case")
public class CriminalCase extends AbstracEntity {
    @Column(name = "number", unique = true)
    private String number;
    @Column(name = "case_type")
    @Enumerated(EnumType.STRING)
    private CaseType caseType;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "detail_description")
    private String detailedDescription;
    @Column(name = "case_status")
    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;
    @Column(name = "notes")
    private String notes;
    @OneToMany(mappedBy = "criminalCase")
    @Transient
    private Set<Evidence> evidenceSet;
    @OneToOne
    @JoinColumn(name="lead_detective_id", nullable = false)
    private Detective leadInvestigator;
    @ManyToMany
    @JoinTable(name = "case_detective")
    @Transient
    private Set<Detective> assigned;

    @Override
    public String toString() {
        return "CriminalCase{" +
                "number='" + number + '\'' +
                ", caseType=" + caseType +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", caseStatus=" + caseStatus +
                ", notes='" + notes + '\'' +
                ", evidenceSet=" + evidenceSet +
                ", leadInvestigator=" + leadInvestigator +
                ", assigned=" + assigned +
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
        CriminalCase that = (CriminalCase) o;
        return Objects.equals(number, that.number) && caseType == that.caseType && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(detailedDescription, that.detailedDescription) && caseStatus == that.caseStatus && Objects.equals(notes, that.notes) && Objects.equals(evidenceSet, that.evidenceSet) && Objects.equals(leadInvestigator, that.leadInvestigator) && Objects.equals(assigned, that.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, caseType, shortDescription, detailedDescription, caseStatus, notes, evidenceSet, leadInvestigator, assigned);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getCaseType() {
        return caseType;
    }

    public void setCaseType(CaseType caseType) {
        this.caseType = caseType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    public void setLeadInvestigator(Detective leadInvestigator) {
        this.leadInvestigator = leadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assigned) {
        this.assigned = assigned;
    }
}
