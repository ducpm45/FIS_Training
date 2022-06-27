package com.fis.sprint_4.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CriminalCaseDTO {
    @NotBlank
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private Integer version;
    @NotBlank
    private String caseNumber;
    private String caseType;
    private String caseStatus;
    private String notes;
    private String shortDescription;
    private String detailDescription;
    private Set<EvidenceDTO> evidenceSet;
    private DetectiveDTO leadInvestigator;
    @JsonIgnore
    private Set<DetectiveDTO> detectiveSet;
}
