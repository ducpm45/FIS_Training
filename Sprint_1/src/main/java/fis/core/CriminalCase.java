package fis.core;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CriminalCase {
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String number;
    private CaseType type;
    private String shortDescription;
    private CaseStatus status;
    private String notes;
    private Set<Evidence> evidenceSet;
    private Detective leadInvestigator;
    private Set<Detective> assigned;

    /**
     * Builder class
     */
    public static class Builder {
        private long id = 2L;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime modifiedAt = LocalDateTime.now();
        private String number;
        private CaseType type;
        private String shortDescription = "";
        private CaseStatus status;
        private String notes;
        private Set<Evidence> evidenceSet = null;
        private Detective leadInvestigator = null;
        private Set<Detective> assigned = null;

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

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setType(CaseType type) {
            this.type = type;
            return this;
        }

        public Builder setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder setStatus(CaseStatus status) {
            this.status = status;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setEvidenceSet(Set<Evidence> evidenceSet) {
            this.evidenceSet = evidenceSet;
            return this;
        }

        public Builder setLeadInvestigator(Detective leadInvestigator) {
            this.leadInvestigator = leadInvestigator;
            return this;
        }

        public Builder setAssigned(Set<Detective> assigned) {
            this.assigned = assigned;
            return this;
        }

        public CriminalCase build() {
            return new CriminalCase(this);
        }
    }

    private CriminalCase() {
        super();
    }

    private CriminalCase(Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        modifiedAt = builder.modifiedAt;
        number = builder.number;
        type = builder.type;
        shortDescription = builder.shortDescription;
        status = builder.status;
        notes = builder.notes;
        evidenceSet = builder.evidenceSet;
        leadInvestigator = builder.leadInvestigator;
        assigned = builder.assigned;
    }

    /**
     * get id value
     *
     * @return criminal case id
     */
    public long getId() {
        return id;
    }

    /**
     * get criminal case creation date
     *
     * @return date
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * get criminal case modify date
     *
     * @return date
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * get case number value
     *
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * get case type
     *
     * @return type
     */
    public CaseType getType() {
        return type;
    }

    /**
     * get case short description
     *
     * @return short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * get case status
     *
     * @return status
     */
    public CaseStatus getStatus() {
        return status;
    }

    /**
     * get case notes
     *
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * get evidences related to this case
     *
     * @return HashSet<Evidence>
     */
    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    /**
     * get the detective is leading on this case
     *
     * @return detective
     */
    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    /**
     * get Detectives are involved in this case
     *
     * @return HashSet<Detective>
     */
    public Set<Detective> getAssigned() {
        return assigned;
    }

    @Override
    public String toString() {
        return "CriminalCase{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", shortDescription='" + shortDescription + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", evidenceSet=" + evidenceSet +
                ", leadInvestigator=" + leadInvestigator +
                ", assigned=" + assigned +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriminalCase that = (CriminalCase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
