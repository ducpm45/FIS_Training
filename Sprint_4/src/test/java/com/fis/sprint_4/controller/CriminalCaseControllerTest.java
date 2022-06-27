package com.fis.sprint_4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.sprint_4.core.CaseStatus;
import com.fis.sprint_4.core.CaseType;
import com.fis.sprint_4.dto.CriminalCaseDTO;
import com.fis.sprint_4.model.CriminalCase;
import com.fis.sprint_4.service.CriminalCaseService;
import com.fis.sprint_4.service.DetectiveService;
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

@WebMvcTest(CriminalCaseController.class)
class CriminalCaseControllerTest {
    @MockBean
    private CriminalCaseService criminalCaseService;
    @MockBean
    private DetectiveService detectiveService;
    @MockBean
    private EvidenceService evidenceService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCriminalCase() throws Exception {
        CriminalCaseDTO criminalCaseDTO = CriminalCaseDTO.builder()
                .caseType("INFRACTION")
                .caseStatus("SUBMITTED")
                .caseNumber("case001")
                .build();
        CriminalCase criminalCase = modelMapper.map(criminalCaseDTO, CriminalCase.class);
        Mockito.when(criminalCaseService.create(criminalCase)).thenReturn(criminalCase);

        ResultActions response = mockMvc.perform(post("/sprint4/criminal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criminalCaseDTO)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.caseNumber",
                        is(criminalCaseDTO.getCaseNumber())))
                .andExpect(jsonPath("$.caseStatus",
                        is(criminalCaseDTO.getCaseStatus())))
                .andExpect(jsonPath("$.caseType",
                        is(criminalCaseDTO.getCaseType())));
    }

    @Test
    void getAllCriminalCase() throws Exception {
        CriminalCase criminalCase1 = new CriminalCase();
        criminalCase1.setCaseNumber("case1");
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseNumber("case2");
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        criminalCaseList.add(criminalCase1);
        criminalCaseList.add(criminalCase2);

        Mockito.when(criminalCaseService.getAll()).thenReturn(criminalCaseList);

        ResultActions response = mockMvc.perform(get("/sprint4/criminal"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(criminalCaseList.size())));
    }

    @Test
    void updateCriminalCase() throws Exception {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseStatus(CaseStatus.IN_COURT);
        criminalCase.setCaseType(CaseType.MISDEMEANOR);
        criminalCase.setCaseNumber("Case");
        criminalCase.setNotes("Note");

        Mockito.when(criminalCaseService.update(criminalCase)).thenReturn(criminalCase);
        CriminalCaseDTO criminalCaseDTO = modelMapper.map(criminalCase, CriminalCaseDTO.class);
        ResultActions response = mockMvc.perform(put("/sprint4/criminal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criminalCase)));

        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.caseNumber",
                        is(criminalCaseDTO.getCaseNumber())))
                .andExpect(jsonPath("$.caseStatus",
                        is(criminalCaseDTO.getCaseStatus())))
                .andExpect(jsonPath("$.caseType",
                        is(criminalCaseDTO.getCaseType())))
                .andExpect(jsonPath("$.notes",
                        is(criminalCaseDTO.getNotes())));
    }

    @Test
    void deleteCriminalCase() throws Exception {
        Long id = 1L;
        willDoNothing().given(criminalCaseService).delete(id);

        ResultActions response = mockMvc.perform(delete("/sprint4/criminal/{id}", id));
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getCaseByStatus() throws Exception {
        String caseStatus = "SUBMITTED";
        CriminalCase criminalCase1 = new CriminalCase();
        criminalCase1.setCaseStatus(CaseStatus.SUBMITTED);
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseStatus(CaseStatus.SUBMITTED);
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        criminalCaseList.add(criminalCase1);
        criminalCaseList.add(criminalCase2);

        Mockito.when(criminalCaseService.getCaseByStatus(CaseStatus.SUBMITTED)).thenReturn(criminalCaseList);

        ResultActions response = mockMvc.perform(get("/sprint4/criminal/status/{caseStatus}", caseStatus));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(criminalCaseList.size())));
    }

}