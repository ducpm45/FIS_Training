package com.fis.sprint_4.controller;

import com.fis.sprint_4.dto.EvidenceDTO;
import com.fis.sprint_4.model.Evidence;
import com.fis.sprint_4.service.EvidenceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sprint4/evidence")
public class EvidenceController {
    private final EvidenceService evidenceService;
    private final ModelMapper modelMapper;

    public EvidenceController(EvidenceService evidenceService, ModelMapper modelMapper) {
        this.evidenceService = evidenceService;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvidenceDTO createEvidence(@RequestBody EvidenceDTO evidenceDTO) {
        Evidence evidenceEntity = modelMapper.map(evidenceDTO, Evidence.class);

        Evidence createdEvidence = evidenceService.create(evidenceEntity);

        return modelMapper.map(createdEvidence, EvidenceDTO.class);

    }

    @GetMapping
    public List<EvidenceDTO> getAllEvidence() {
        return evidenceService.getAll().stream()
                .map(evidence -> modelMapper.map(evidence, EvidenceDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<EvidenceDTO> updateDetective(@RequestBody EvidenceDTO evidenceDTO) {
        Evidence evidence = modelMapper.map(evidenceDTO, Evidence.class);
        Evidence updatedEvidence = evidenceService.update(evidence);
        return new ResponseEntity<>(modelMapper.map(updatedEvidence, EvidenceDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvidence(@PathVariable("id") Long id) {
        evidenceService.delete(id);
        return new ResponseEntity<>("Evidence deleted successfully!", HttpStatus.OK);
    }
}
