package com.fis.sprint_4.dto;

import com.fis.sprint_4.core.TrackAction;
import com.fis.sprint_4.model.Detective;
import com.fis.sprint_4.model.Evidence;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TrackEntryDTO {
    @NotBlank
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private Integer version;
    private LocalDateTime date;
    private Evidence evidence;
    private Detective detective;
    private TrackAction trackAction;
    private String reason;
}
