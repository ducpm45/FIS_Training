package com.fis.sprint_4.repository;

import com.fis.sprint_4.core.EmploymentStatus;
import com.fis.sprint_4.model.Detective;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DetectiveRepoTest {
    @Autowired
    private DetectiveRepo detectiveRepo;

    @Test
    void testCreateReadDelete() {
        Detective detective = new Detective();
        detective.setEmploymentStatus(EmploymentStatus.UNDER_INVESTIGATION);
        detectiveRepo.save(detective);
        Iterable<Detective> detectives = detectiveRepo.findAll();
        Assertions.assertThat(detectives).extracting(Detective::getEmploymentStatus)
                .containsOnly(EmploymentStatus.UNDER_INVESTIGATION);
        detective.setArmed(true);
        detective.setFirstName("Pam");
        detective.setLastName("Duc");
        detective.setBadgeNumber("007");
        detectiveRepo.save(detective);
        Assertions.assertThat("007".equals(detectiveRepo.findById(1L).orElseThrow().getBadgeNumber()));
        System.out.println(detectiveRepo.findById(1L));
        detectiveRepo.deleteAll();
        Assertions.assertThat(detectiveRepo.findAll()).isEmpty();
    }

}