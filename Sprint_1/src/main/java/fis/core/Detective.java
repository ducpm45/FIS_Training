package fis.core;

import java.time.LocalDateTime;
import java.util.Set;

public class Detective {
    private Long id = 1L;
    private LocalDateTime modifiedAt = LocalDateTime.now();
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate = LocalDateTime.now();
    private String badgeNumber;
    private Rank rank;
    private boolean armed = true;
    private EmploymentStatus status;
    private Set<CriminalCase> criminalCases = null;
    private Set<TrackEntry> trackEntries = null;

    /**
     * Builder class
     */
    public static class Builder {
        private Long id;
        private LocalDateTime modifiedAt = LocalDateTime.now();
        private String userName;
        private String firstName;
        private String lastName;
        private String password;
        private LocalDateTime hiringDate = LocalDateTime.now();
        private String badgeNumber;
        private Rank rank;
        private boolean armed = true;
        private EmploymentStatus status;
        private Set<CriminalCase> criminalCases;
        private Set<TrackEntry> trackEntries;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setHiringDate(LocalDateTime hiringDate) {
            this.hiringDate = hiringDate;
            return this;
        }

        public Builder setBadgeNumber(String badgeNumber) {
            this.badgeNumber = badgeNumber;
            return this;
        }

        public Builder setRank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public Builder setArmed(boolean armed) {
            this.armed = armed;
            return this;
        }

        public Builder setStatus(EmploymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCriminalCases(Set<CriminalCase> criminalCases) {
            this.criminalCases = criminalCases;
            return this;
        }

        public Builder setTrackEntries(Set<TrackEntry> trackEntries) {
            this.trackEntries = trackEntries;
            return this;
        }

        public Detective build() {
            return new Detective(this);
        }
    }

    private Detective() {
        super();
    }

    private Detective(Builder builder) {
        userName = builder.userName;
        firstName = builder.firstName;
        lastName = builder.lastName;
        password = builder.password;
        hiringDate = builder.hiringDate;
        badgeNumber = builder.badgeNumber;
        rank = builder.rank;
        armed = builder.armed;
        status = builder.status;
        criminalCases = builder.criminalCases;
        trackEntries = builder.trackEntries;
    }

    /**
     * Get id value
     *
     * @return id value
     */
    public Long getId() {
        return id;
    }

    /**
     * Get modified date time
     *
     * @return modified date time
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * get username
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * get first name
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get last name
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * get password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * get hiring date
     *
     * @return hiring date
     */
    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    /**
     * get badge number
     *
     * @return badge number
     */
    public String getBadgeNumber() {
        return badgeNumber;
    }

    /**
     * get detective rank
     *
     * @return rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * get armed, if true -> detective armed, false -> non-armed
     *
     * @return armed
     */
    public boolean isArmed() {
        return armed;
    }

    /**
     * get detective status
     *
     * @return status
     */
    public EmploymentStatus getStatus() {
        return status;
    }

    /**
     * get the case that detective are working on
     *
     * @return HashSet<CriminalCase>
     */
    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    /**
     * get track entries that detective are signing up for
     *
     * @return HashSet<TrackEntry>
     */
    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    @Override
    public String toString() {
        return "Detective{" +
                "id=" + id +
                ", modifiedAt=" + modifiedAt +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", hiringDate=" + hiringDate +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", rank=" + rank +
                ", armed=" + armed +
                ", status=" + status +
                ", criminalCases=" + criminalCases +
                ", trackEntries=" + trackEntries +
                '}';
    }
}
