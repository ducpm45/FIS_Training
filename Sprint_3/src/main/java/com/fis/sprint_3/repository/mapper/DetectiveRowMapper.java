package com.fis.sprint_3.repository.mapper;

import com.fis.sprint_3.core.EmploymentStatus;
import com.fis.sprint_3.core.Rank;
import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetectiveRowMapper implements RowMapper<Detective> {

    @Override
    public Detective mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String badgeNumber = rs.getString("badge_number");
        String rank = rs.getString("rank");
        boolean armed = rs.getBoolean("armed");
        String status = rs.getString("employmentStatus");
        long personId = rs.getLong("person_id");

        Person person = new Person();
        person.setId(personId);
        person.setUserName(rs.getString("user_name"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setHiringDate(rs.getTimestamp("hiring_date").toLocalDateTime());

        Detective detective = new Detective();
        detective.setId(id);
        detective.setPerson(person);
        detective.setBadgeNumber(badgeNumber);
        detective.setRank(Rank.valueOf(rank));
        detective.setArmed(armed);
        detective.setEmploymentStatus(EmploymentStatus.valueOf(status));
        return detective;
    }
}
