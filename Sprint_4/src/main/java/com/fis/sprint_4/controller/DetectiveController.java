package com.fis.sprint_4.controller;

import com.fis.sprint_4.dto.DetectiveDTO;
import com.fis.sprint_4.model.Detective;
import com.fis.sprint_4.service.DetectiveService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sprint4/detective")
public class DetectiveController {
    private final DetectiveService detectiveService;
    private final ModelMapper modelMapper;

    public DetectiveController(DetectiveService detectiveService, ModelMapper modelMapper) {
        this.detectiveService = detectiveService;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetectiveDTO createDetective(@RequestBody DetectiveDTO detectiveDTO) {
        Detective detectiveEntity = modelMapper.map(detectiveDTO, Detective.class);

        Detective createdDetective = detectiveService.create(detectiveEntity);

        return modelMapper.map(createdDetective, DetectiveDTO.class);

    }

    @GetMapping
    public List<DetectiveDTO> getAllDetective() {
        return detectiveService.getAll().stream()
                .map(detective -> modelMapper.map(detective, DetectiveDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<DetectiveDTO> updateDetective(@RequestBody DetectiveDTO detectiveDTO) {
        Detective detective = modelMapper.map(detectiveDTO, Detective.class);
        Detective updatedDetective = detectiveService.update(detective);
        return new ResponseEntity<>(modelMapper.map(updatedDetective, DetectiveDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetective(@PathVariable("id") Long id) {
        detectiveService.delete(id);
        return new ResponseEntity<>("Detective deleted successfully!", HttpStatus.OK);
    }
}
