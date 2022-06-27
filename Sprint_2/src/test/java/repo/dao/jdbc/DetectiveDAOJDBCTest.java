package repo.dao.jdbc;

import core.Detective;
import core.enums.EmploymentStatus;
import core.enums.Rank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
class DetectiveDAOJDBCTest {
    @BeforeEach
    void beforeEach() {
        Detective d1 = new Detective.Builder()
                .setId(1)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("minhduc")
                .setFirstName("Pham")
                .setLastName("Minh Duc")
                .setPassword("minhduc1@")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH001")
                .setRank(Rank.JUNIOR)
                .setArmed(true)
                .setStatus(EmploymentStatus.ACTIVE)
                .build();
        Detective d2 = new Detective.Builder()
                .setId(2)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("thodung")
                .setFirstName("Le")
                .setLastName("Tho Dung")
                .setPassword("dung123@")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH002")
                .setRank(Rank.INSPECTOR)
                .setArmed(true)
                .setStatus(EmploymentStatus.ACTIVE)
                .build();
        Detective d3 = new Detective.Builder()
                .setId(3)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("vanngon")
                .setFirstName("Nguyen")
                .setLastName("Van Ngon")
                .setPassword("ngonnv123#")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH003")
                .setRank(Rank.SENIOR)
                .setArmed(true)
                .setStatus(EmploymentStatus.UNDER_INVESTIGATION)
                .build();
        Detective d4 = new Detective.Builder()
                .setId(4)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("thuthuy")
                .setFirstName("Nguyen Thi")
                .setLastName("Thu Thuy")
                .setPassword("thuy011094!")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH004")
                .setRank(Rank.TRAINEE)
                .setArmed(false)
                .setStatus(EmploymentStatus.RETIRED)
                .build();
        Detective d5 = new Detective.Builder()
                .setId(5)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("ngochai")
                .setFirstName("Le")
                .setLastName("Ngoc Hai")
                .setPassword("ngochai123!")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH005")
                .setRank(Rank.SENIOR)
                .setArmed(true)
                .setStatus(EmploymentStatus.VACATION)
                .build();
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        ddj.create(d1);
        ddj.create(d2);
        ddj.create(d3);
        ddj.create(d4);
        ddj.create(d5);
    }
    @Test
    void create() {
        Detective d6 = new Detective.Builder()
                .setId(6)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("dinhchien")
                .setFirstName("Vu")
                .setLastName("Dinh Chien")
                .setPassword("vuchien123@")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH006")
                .setRank(Rank.JUNIOR)
                .setArmed(true)
                .setStatus(EmploymentStatus.ACTIVE)
                .build();
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        Assertions.assertEquals(5, ddj.getAll().get().size());
        ddj.create(d6);
        Assertions.assertEquals(6, ddj.getAll().get().size());
    }

    @Test
    void getAll() {
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        Assertions.assertEquals(5, ddj.getAll().get().size());
    }

    @Test
    void update() {
        Detective d2 = new Detective.Builder()
                .setId(2)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("trannga")
                .setFirstName("Tran")
                .setLastName("Thi Nga")
                .setPassword("ngatran123@")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH002")
                .setRank(Rank.INSPECTOR)
                .setArmed(false)
                .setStatus(EmploymentStatus.ACTIVE)
                .build();
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        ddj.update(d2);
        Assertions.assertEquals("ngatran123@", ddj.findByBadgeNumber("HH002").getPassword());
    }

    @Test
    void delete() {
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        Detective c = ddj.findByBadgeNumber("HH003");
        ddj.delete(c);
        Assertions.assertEquals(4, ddj.getAll().get().size());
    }

    @Test
    void findByBadgeNumber() {
        String badgeNumber = "HH003";
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        Assertions.assertEquals("vanngon", ddj.findByBadgeNumber(badgeNumber).getUserName());
    }

    @AfterEach
    void afterEach() {
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        ddj.deleteAll();
    }
}