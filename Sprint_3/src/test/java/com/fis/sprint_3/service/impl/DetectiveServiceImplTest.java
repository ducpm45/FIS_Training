package com.fis.sprint_3.service.impl;

import com.fis.sprint_3.core.EmploymentStatus;
import com.fis.sprint_3.core.Rank;
import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.model.Person;
import com.fis.sprint_3.service.DetectiveService;
import com.fis.sprint_3.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DetectiveServiceImplTest {
    @Autowired
    private DetectiveService detectiveService;
    @Autowired
    private PersonService personService;

    @Test
    @Order(1)
    void create() {
        Person person = new Person();
        person.setUserName("minhduc");
        person.setFirstName("Pham Minh");
        person.setLastName("Duc");
        person.setPassword("123456");
        personService.create(person);
        Detective detective = new Detective();
        detective.setRank(Rank.INSPECTOR);
        detective.setEmploymentStatus(EmploymentStatus.RETIRED);
        detective.setArmed(true);
        detective.setBadgeNumber("DT001");
        detective.setPerson(person);
        detectiveService.create(detective);
    }

    @Test
    @Order(2)
    void getALl() {
        Assertions.assertEquals(2, detectiveService.getALl().size());
        detectiveService.getALl().forEach(System.out::println);
    }

    @Test
    @Order(3)
    void findById() {
        Detective detective = detectiveService.findById(1L);
        Assertions.assertEquals(Rank.CHIEF_INSPECTOR, detective.getRank());
        Assertions.assertEquals("Thu Thuy", detective.getPerson().getLastName());
        System.out.println(detective);
    }

    @Test
    @Order(4)
    void update() {
        Detective detective = detectiveService.findById(1L);
        Detective detective1 = detectiveService.findById(2L);
        detective.setVersion(1);
        detective1.setArmed(false);
        detectiveService.update(detective);
        detectiveService.update(detective1);
        Assertions.assertEquals(1, detective.getVersion());
        Assertions.assertFalse(detective1.getArmed());
    }

    @Test
    @Order(5)
    void deleteById() {
        detectiveService.deleteById(3L);
        Assertions.assertNotEquals(3, detectiveService.getALl().size());
    }
}