package com.fis.sprint_4.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvidenceDTO {
    @NotBlank
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private Long caseId;
    private Long storageId;
    private Integer version;
    @NotBlank
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;
    private Set<TrackEntryDTO> trackEntrySet;
}
