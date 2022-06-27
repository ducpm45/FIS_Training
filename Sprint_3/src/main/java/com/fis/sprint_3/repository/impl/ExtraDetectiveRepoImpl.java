package com.fis.sprint_3.repository.impl;

import com.fis.sprint_3.core.EmploymentStatus;
import com.fis.sprint_3.core.Rank;
import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("extraDetectiveRepoImpl")
public class ExtraDetectiveRepoImpl extends DetectiveRepoImpl {

    public ExtraDetectiveRepoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private class DetectiveExtractor implements ResultSetExtractor<Detective> {
        @Override
        public Detective extractData(ResultSet rs) throws SQLException {
            Detective detective = null;
            while (rs.next()) {
                if (detective == null) {
                    detective = new Detective();
                    // set internal entity identifier (primary key)
                    detective.setId(rs.getLong("id"));
                    detective.setBadgeNumber(rs.getString("badge_number"));
                    detective.setRank(Rank.valueOf(rs.getString("`rank`")));
                    detective.setArmed(rs.getBoolean("armed"));
                    detective.setEmploymentStatus(EmploymentStatus.valueOf(rs.getString("employmentStatus")));
                }
                Person p = new Person();
                p.setId(rs.getLong("id"));
                p.setUserName(rs.getString("user_name"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setHiringDate(rs.getTimestamp("hiring_date").toLocalDateTime());
                detective.setPerson(p);
            }

            return detective;
        }
    }
}
