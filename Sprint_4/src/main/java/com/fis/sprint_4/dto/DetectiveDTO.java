package com.fis.sprint_4.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DetectiveDTO {
    @NotBlank
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private Integer version;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDateTime hiringDate;
    private String password;
    @NotBlank
    private String badgeNumber;
    private Boolean armed;
    private String rank;
    private String employmentStatus;
    private Set<CriminalCaseDTO> criminalCaseSet;
    private Set<TrackEntryDTO> trackEntrySet;
}
