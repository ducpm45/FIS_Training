package com.fis.sprint_4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.sprint_4.dto.EvidenceDTO;
import com.fis.sprint_4.model.Evidence;
import com.fis.sprint_4.service.EvidenceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EvidenceController.class)
class EvidenceControllerTest {
    @MockBean
    private EvidenceService evidenceService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createEvidence() throws Exception {
        EvidenceDTO evidenceDTO = EvidenceDTO.builder()
                .archived(true)
                .itemName("Knife")
                .number("EVIDENCE001")
                .build();
        Evidence evidence = modelMapper.map(evidenceDTO, Evidence.class);
        Mockito.when(evidenceService.create(evidence)).thenReturn(evidence);

        ResultActions response = mockMvc.perform(post("/sprint4/evidence")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evidenceDTO)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemName",
                        is(evidenceDTO.getItemName())))
                .andExpect(jsonPath("$.archived",
                        is(evidenceDTO.getArchived())))
                .andExpect(jsonPath("$.number",
                        is(evidenceDTO.getNumber())));
    }

    @Test
    void getAllEvidence() throws Exception {
        Evidence evidence1 = new Evidence();
        evidence1.setItemName("Knife");
        Evidence evidence2 = new Evidence();
        evidence2.setItemName("Gun");
        List<Evidence> evidenceList = new ArrayList<>();
        evidenceList.add(evidence1);
        evidenceList.add(evidence2);

        Mockito.when(evidenceService.getAll()).thenReturn(evidenceList);

        ResultActions response = mockMvc.perform(get("/sprint4/evidence"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(evidenceList.size())));
    }

    @Test
    void updateDetective() throws Exception {
        Evidence evidence = new Evidence();
        evidence.setNotes("Note");
        evidence.setItemName("Knife");
        evidence.setNumber("evidence1");

        Mockito.when(evidenceService.update(evidence)).thenReturn(evidence);
        EvidenceDTO evidenceDTO = modelMapper.map(evidence, EvidenceDTO.class);
        ResultActions response = mockMvc.perform(put("/sprint4/evidence")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evidence)));

        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.notes",
                        is(evidenceDTO.getNotes())))
                .andExpect(jsonPath("$.itemName",
                        is(evidenceDTO.getItemName())))
                .andExpect(jsonPath("$.number",
                        is(evidenceDTO.getNumber())));
    }

    @Test
    void deleteEvidence() throws Exception {
        Long id = 1L;
        willDoNothing().given(evidenceService).delete(id);

        ResultActions response = mockMvc.perform(delete("/sprint4/evidence/{id}", id));
        response.andExpect(status().isOk())
                .andDo(print());
    }
}