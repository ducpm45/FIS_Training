package com.fis.sprint_4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.sprint_4.core.EmploymentStatus;
import com.fis.sprint_4.dto.DetectiveDTO;
import com.fis.sprint_4.model.Detective;
import com.fis.sprint_4.service.DetectiveService;
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

@WebMvcTest(DetectiveController.class)
class DetectiveControllerTest {
    @MockBean
    private DetectiveService detectiveService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createDetective() throws Exception {
        DetectiveDTO detectiveDTO = DetectiveDTO.builder()
                .badgeNumber("Detective001")
                .employmentStatus("ACTIVE")
                .rank("SENIOR")
                .build();
        Detective detective = modelMapper.map(detectiveDTO, Detective.class);
        Mockito.when(detectiveService.create(detective)).thenReturn(detective);

        ResultActions response = mockMvc.perform(post("/sprint4/detective")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(detectiveDTO)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.badgeNumber",
                        is(detectiveDTO.getBadgeNumber())))
                .andExpect(jsonPath("$.employmentStatus",
                        is(detectiveDTO.getEmploymentStatus())))
                .andExpect(jsonPath("$.rank",
                        is(detectiveDTO.getRank())));
    }

    @Test
    void getAllDetective() throws Exception {
        Detective detective1 = new Detective();
        detective1.setBadgeNumber("Detect001");
        Detective detective2 = new Detective();
        detective2.setBadgeNumber("Detect002");
        List<Detective> detectiveList = new ArrayList<>();
        detectiveList.add(detective1);
        detectiveList.add(detective2);

        Mockito.when(detectiveService.getAll()).thenReturn(detectiveList);

        ResultActions response = mockMvc.perform(get("/sprint4/detective"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(detectiveList.size())));
    }

    @Test
    void updateDetective() throws Exception {
        Detective detective = new Detective();
        detective.setBadgeNumber("Detect001");
        detective.setEmploymentStatus(EmploymentStatus.ACTIVE);
        detective.setUserName("detective001");
        detective.setFirstName("John");

        Mockito.when(detectiveService.update(detective)).thenReturn(detective);
        DetectiveDTO detectiveDTO = modelMapper.map(detective, DetectiveDTO.class);
        ResultActions response = mockMvc.perform(put("/sprint4/detective")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(detective)));

        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.badgeNumber",
                        is(detectiveDTO.getBadgeNumber())))
                .andExpect(jsonPath("$.employmentStatus",
                        is(detectiveDTO.getEmploymentStatus())))
                .andExpect(jsonPath("$.userName",
                        is(detectiveDTO.getUserName())))
                .andExpect(jsonPath("$.firstName",
                        is(detectiveDTO.getFirstName())));
    }

    @Test
    void deleteDetective() throws Exception {
        Long id = 1L;
        willDoNothing().given(detectiveService).delete(id);

        ResultActions response = mockMvc.perform(delete("/sprint4/detective/{id}", id));
        response.andExpect(status().isOk())
                .andDo(print());
    }
}