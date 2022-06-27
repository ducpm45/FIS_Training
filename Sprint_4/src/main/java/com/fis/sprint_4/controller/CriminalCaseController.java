package com.fis.sprint_4.controller;

import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.dto.CriminalCaseDTO;
import com.fis.sprint_4.dto.EvidenceDTO;
import com.fis.sprint_4.model.CriminalCase;
import com.fis.sprint_4.service.CriminalCaseService;
import com.fis.sprint_4.service.DetectiveService;
import com.fis.sprint_4.service.EvidenceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sprint4/criminal")
public class CriminalCaseController {
    private final CriminalCaseService criminalCaseService;
    private final DetectiveService detectiveService;
    private final EvidenceService evidenceService;
    private final ModelMapper modelMapper;

    public CriminalCaseController(CriminalCaseService criminalCaseService, ModelMapper modelMapper, EvidenceService evidenceService, DetectiveService detectiveService) {
        this.criminalCaseService = criminalCaseService;
        this.modelMapper = modelMapper;
        this.evidenceService = evidenceService;
        this.detectiveService = detectiveService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriminalCaseDTO createCriminalCase(@RequestBody CriminalCaseDTO criminalCaseDTO) {
        CriminalCase criminalCaseEntity = modelMapper.map(criminalCaseDTO, CriminalCase.class);

        CriminalCase createdCriminalCase = criminalCaseService.create(criminalCaseEntity);

        return modelMapper.map(createdCriminalCase, CriminalCaseDTO.class);

    }

    @GetMapping
    public List<CriminalCaseDTO> getAllCriminalCase() {
        return criminalCaseService.getAll().stream()
                .map(criminalCase -> modelMapper.map(criminalCase, CriminalCaseDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<CriminalCaseDTO> updateCriminalCase(@RequestBody CriminalCaseDTO criminalCaseDTO) {
        CriminalCase criminalCase = modelMapper.map(criminalCaseDTO, CriminalCase.class);
        CriminalCase updatedCriminalCase = criminalCaseService.update(criminalCase);
        return new ResponseEntity<>(modelMapper.map(updatedCriminalCase, CriminalCaseDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCriminalCase(@PathVariable("id") Long id) {
        criminalCaseService.delete(id);
        return new ResponseEntity<>("Criminal Case deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("status/{caseStatus}")
    public List<CriminalCaseDTO> getCaseByStatus(@PathVariable("caseStatus") String caseStatus) {
        return criminalCaseService.getCaseByStatus(CaseStatus.valueOf(caseStatus)).stream()
                .map(criminalCase -> modelMapper.map(criminalCase, CriminalCaseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("number/{caseNumber}")
    public List<EvidenceDTO> getEvidencesByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return evidenceService.getEvidencesByCriminalCase_CaseNumber(caseNumber).stream()
                .map(evidence -> modelMapper.map(evidence, EvidenceDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/userName/{userName}")
    public List<CriminalCaseDTO> getCriminalCasesByDetectiveUserName(@PathVariable("userName") String userName) {
        Long detectiveId = detectiveService.findByUserName(userName).getId();
        return criminalCaseService.findCriminalCasesByDetectiveId(detectiveId).stream()
                .map(criminalCase -> modelMapper.map(criminalCase, CriminalCaseDTO.class)).collect(Collectors.toList());
    }
}
