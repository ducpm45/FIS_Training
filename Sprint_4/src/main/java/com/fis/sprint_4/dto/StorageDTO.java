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
public class StorageDTO {
    @NotBlank
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private Integer version;
    @NotBlank
    private String storageName;
    private String location;
    private Set<EvidenceDTO> evidenceSet;
}
