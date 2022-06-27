package com.fis.sprint_4.model;

import com.fis.sprint_4.core.EmploymentStatus;
import com.fis.sprint_4.core.Rank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "detectives")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Detective extends AbstractEntity {

    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password", length = 128)
    private String password;
    @Column(name = "hiring_date")
    private LocalDateTime hiringDate;
    @Column(name = "badge_number", unique = true)
    private String badgeNumber;
    @Column(name = "armed")
    private boolean armed;
    @Column(name = "`rank`")
    @Enumerated(EnumType.STRING)
    private Rank rank;
    @Column(name = "empl_status")
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "case_detective"
            , joinColumns = {@JoinColumn(name = "detective_id")}
            , inverseJoinColumns = {@JoinColumn(name = "case_id")})
    private Set<CriminalCase> criminalCaseSet;

    @OneToMany(mappedBy = "detective", fetch = FetchType.LAZY)
    private Set<TrackEntry> trackEntrySet;

    public Detective() {
        hiringDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Detective{" +
                "id=" + getId() +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                ", version=" + getVersion() +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", hiringDate=" + hiringDate +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", armed=" + armed +
                ", rank=" + rank +
                ", employmentStatus=" + employmentStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detective detective = (Detective) o;

        if (armed != detective.armed) return false;
        if (!Objects.equals(userName, detective.userName)) return false;
        if (!Objects.equals(firstName, detective.firstName)) return false;
        if (!Objects.equals(lastName, detective.lastName)) return false;
        if (!Objects.equals(password, detective.password)) return false;
        if (!Objects.equals(hiringDate, detective.hiringDate)) return false;
        if (!Objects.equals(badgeNumber, detective.badgeNumber))
            return false;
        if (rank != detective.rank) return false;
        return employmentStatus == detective.employmentStatus;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (hiringDate != null ? hiringDate.hashCode() : 0);
        result = 31 * result + (badgeNumber != null ? badgeNumber.hashCode() : 0);
        result = 31 * result + (armed ? 1 : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (employmentStatus != null ? employmentStatus.hashCode() : 0);
        return result;
    }
}
