package repo.dao.jdbc;

import core.CriminalCase;
import core.Detective;
import core.enums.CaseStatus;
import core.enums.CaseType;
import core.enums.EmploymentStatus;
import core.enums.Rank;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;

class CriminalCaseDAOJDBCTest {
    private static final Logger log = LoggerFactory.getLogger(CriminalCaseDAOJDBC.class);
    @BeforeEach
    void beforeEach() throws SQLException {
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
        CriminalCase c1 = new CriminalCase.Builder()
                .setId(1)
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now())
                .setNumber("HS001")
                .setType(CaseType.FELONY)
                .setShortDescription("Vu an 1")
                .setStatus(CaseStatus.IN_COURT)
                .setNotes("Chu y 1")
                .setLeadInvestigator(d1)
                .build();
        CriminalCase c2 = new CriminalCase.Builder()
                .setId(2)
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now())
                .setNumber("HS002")
                .setType(CaseType.INFRACTION)
                .setShortDescription("Vu an 2")
                .setStatus(CaseStatus.DISMISSED)
                .setNotes("Chu y 2")
                .setLeadInvestigator(d2)
                .build();
        CriminalCase c3 = new CriminalCase.Builder()
                .setId(3)
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now())
                .setNumber("HS003")
                .setType(CaseType.UNCATEGORIZED)
                .setShortDescription("Vu an 3")
                .setStatus(CaseStatus.COLD)
                .setNotes("Chu y 1")
                .setLeadInvestigator(d3)
                .build();
        CriminalCase c4 = new CriminalCase.Builder()
                .setId(4)
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now())
                .setNumber("HS004")
                .setType(CaseType.INFRACTION)
                .setShortDescription("Vu an 4")
                .setStatus(CaseStatus.CLOSED)
                .setNotes("Chu y 1")
                .setLeadInvestigator(d1)
                .build();
        CriminalCase c5 = new CriminalCase.Builder()
                .setId(5)
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now())
                .setNumber("HS005")
                .setType(CaseType.MISDEMEANOR)
                .setShortDescription("Vu an 5")
                .setStatus(CaseStatus.SUBMITTED)
                .setNotes("Chu y 5")
                .setLeadInvestigator(d5)
                .build();
        CriminalCaseDAOJDBC ccdj = new CriminalCaseDAOJDBC();
        ccdj.create(c1);
        ccdj.create(c2);
        ccdj.create(c3);
        ccdj.create(c4);
        ccdj.create(c5);
    }
    @Test
    void create() throws SQLException {
        Detective d6 = new Detective.Builder()
                .setId(6)
                .setModifiedAt(LocalDateTime.now())
                .setUserName("minhduc")
                .setFirstName("Pham")
                .setLastName("Minh Duc")
                .setPassword("minhduc1@")
                .setHiringDate(LocalDateTime.now())
                .setBadgeNumber("HH006")
                .setRank(Rank.JUNIOR)
                .setArmed(true)
                .setStatus(EmploymentStatus.ACTIVE)
                .build();
        CriminalCase c6 = new CriminalCase.Builder()
                .setId(5)
                .setNumber("HS006")
                .setType(CaseType.FELONY)
                .setStatus(CaseStatus.IN_COURT)
                .setNotes("adfg")
                .setLeadInvestigator(d6)
                .build();

        CriminalCaseDAOJDBC dao = new CriminalCaseDAOJDBC();
        dao.create(c6);
        Assertions.assertEquals(6, dao.getAll().get().size());
    }

    @Test
    void findByNumber() throws SQLException {
        String case_number = "HS002";
        CriminalCaseDAOJDBC dao = new CriminalCaseDAOJDBC();

        log.info(dao.findByNumber(case_number).toString());
    }

    @Test
    void getAll() {
        CriminalCaseDAOJDBC ccdj = new CriminalCaseDAOJDBC();
        Assertions.assertEquals(5, ccdj.getAll().get().size());
        MatcherAssert.assertThat(5, CoreMatchers.is(CoreMatchers.equalTo(ccdj.getAll().get().size())));
    }

    @Test
    void update() {
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
        CriminalCase c1 = new CriminalCase.Builder()
                .setId(2)
                .setNumber("HS003")
                .setType(CaseType.FELONY)
                .setShortDescription("updated")
                .setStatus(CaseStatus.IN_COURT)
                .setNotes("updated")
                .setLeadInvestigator(d1)
                .build();
        CriminalCaseDAOJDBC ccdj = new CriminalCaseDAOJDBC();
        ccdj.update(c1);
        Assertions.assertEquals("updated", ccdj.findByNumber("HS003").getShortDescription());
    }

    @Test
    void delete() {
        CriminalCaseDAOJDBC ccdj = new CriminalCaseDAOJDBC();
        CriminalCase c = ccdj.findByNumber("HS003");
        ccdj.delete(c);
        Assertions.assertEquals(4, ccdj.getAll().get().size());
    }

    @AfterEach
    void afterEach() {
        CriminalCaseDAOJDBC ccdj = new CriminalCaseDAOJDBC();
        ccdj.deleteAll();
    }
}