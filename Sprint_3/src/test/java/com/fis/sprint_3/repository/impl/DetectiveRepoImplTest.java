package com.fis.sprint_3.repository.impl;

import com.fis.sprint_3.core.EmploymentStatus;
import com.fis.sprint_3.core.Rank;
import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.model.Person;
import com.fis.sprint_3.repository.PersonRepo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class DetectiveRepoImplTest {
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    @Qualifier("extraDetectiveRepoImpl")
    private DetectiveRepoImpl detectiveRepo;

    @Test
    @Order(1)
    void create() {
        Person person = new Person();
        person.setUserName("minhduc");
        person.setFirstName("Pham");
        person.setLastName("Minh Duc");
        person.setPassword("123456");
        personRepo.save(person);
        Detective detective = new Detective();
        detective.setRank(Rank.JUNIOR);
        detective.setEmploymentStatus(EmploymentStatus.RETIRED);
        detective.setArmed(true);
        detective.setBadgeNumber("DT001");
        detective.setPerson(person);
        detectiveRepo.create(detective);
    }

    @Test
    @Order(2)
    void getALl() {
        detectiveRepo.getALl().forEach(System.out::println);
    }

    @Test
    @Order(3)
    void findById() {
        System.out.println(detectiveRepo.findById(1L));
    }

    @Test
    @Order(4)
    void update() {
        Detective detective = new Detective();
        detective.setId(1L);
        detective.setVersion(2);
        detective.setArmed(true);
        detective.setEmploymentStatus(EmploymentStatus.UNDER_INVESTIGATION);
        detective.setRank(Rank.INSPECTOR);
        detectiveRepo.update(detective);
    }

    @Test
    @Order(5)
    void deleteById() {
        detectiveRepo.deleteById(3L);
    }
}