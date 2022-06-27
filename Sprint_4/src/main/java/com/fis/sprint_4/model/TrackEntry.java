package com.fis.sprint_4.model;

import com.fis.sprint_4.core.TrackAction;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "track_entries")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TrackEntry extends AbstractEntity {
    @Column(name = "date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;
    @ManyToOne
    @JoinColumn(name = "detective_id")
    private Detective detective;
    @Column(name = "track_action")
    @Enumerated(EnumType.STRING)
    private TrackAction trackAction;
    @Column(name = "reason")
    private String reason;

    public TrackEntry() {
        date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TrackEntry{" +
                "id=" + getId() +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                ", version=" + getVersion() +
                ", date=" + date +
                ", trackAction=" + trackAction +
                ", reason='" + reason + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrackEntry that = (TrackEntry) o;

        if (!Objects.equals(date, that.date)) return false;
        if (trackAction != that.trackAction) return false;
        return Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (trackAction != null ? trackAction.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }
}
