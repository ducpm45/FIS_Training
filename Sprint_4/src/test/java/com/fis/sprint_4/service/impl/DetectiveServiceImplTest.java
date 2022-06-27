package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.core.Rank;
import com.fis.sprint_4.model.Detective;
import com.fis.sprint_4.repository.DetectiveRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class DetectiveServiceImplTest {
   private DetectiveServiceImpl detectiveService;
   private DetectiveRepo detectiveRepo;
   private Detective detective;


    @BeforeEach
    public void init() {
        detectiveRepo = mock(DetectiveRepo.class);
        detectiveService = new DetectiveServiceImpl(detectiveRepo);
        detective = new Detective();
        detective.setId(1L);
        detective.setArmed(true);
        detective.setBadgeNumber("DETECT001");
        detective.setFirstName("Pam");
        detective.setLastName("Duc");
        detective.setUserName("minidump");
    }
    @Test
    void create() {
        when(detectiveRepo.save(detective)).thenReturn(detective);
        Detective savedDetective = detectiveService.create(detective);
        log.info("Saved detective: {}", savedDetective);
        assertThat(savedDetective).isNotNull();
        verify(detectiveRepo, times(1)).save(detective);
    }

    @Test
    void getAll() {
        List<Detective> detectiveList = new ArrayList<>();
        Detective detective1 = new Detective();
        Detective detective2 = new Detective();
        Detective detective3 = new Detective();
        detectiveList.add(detective1);
        detectiveList.add(detective2);
        detectiveList.add(detective3);

        when(detectiveRepo.findAll()).thenReturn(detectiveList);

        List<Detective> detectives = detectiveService.getAll();
        detectives.forEach(detect -> log.info("Detect in list: {}", detect));
        assertEquals(3, detectives.size());
        verify(detectiveRepo, times(1)).findAll();
    }

    @Test
    void update() {
        when(detectiveRepo.save(detective)).thenReturn(detective);
        log.info("Update before: {}", detective);
        detective.setBadgeNumber("DETECT007");
        detective.setRank(Rank.SENIOR);
        Detective updatedDetective = detectiveService.update(detective);
        log.info("Updated detect: {}", updatedDetective);
        assertThat("DETECT007".equals(updatedDetective.getBadgeNumber()));
        assertThat(Rank.SENIOR.equals(updatedDetective.getRank()));
    }

    @Test
    void findById() {
        when(detectiveRepo.findById(1L)).thenReturn(Optional.of(detective));
        Detective savedDetective = detectiveService.findById(detective.getId());
        log.info("Find by id detect: {}", savedDetective);
        assertThat(savedDetective).isNotNull();
    }

    @Test
    void delete() {
        detectiveService.delete(detective.getId());
        verify(detectiveRepo, times(1)).deleteById(detective.getId());
    }

    @Test
    void findByUserName() {
        when(detectiveRepo.findByUserName("minidump")).thenReturn(detective);
        Detective detective1 = detectiveService.findByUserName("minidump");
        log.info("Detective: {}", detective1);
        assertThat("DETECT001".equals(detective1.getBadgeNumber()));
        verify(detectiveRepo, times(1)).findByUserName("minidump");
    }
}