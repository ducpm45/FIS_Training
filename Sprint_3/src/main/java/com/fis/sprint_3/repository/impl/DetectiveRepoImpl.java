package com.fis.sprint_3.repository.impl;

import com.fis.sprint_3.model.Detective;
import com.fis.sprint_3.repository.DetectiveRepo;
import com.fis.sprint_3.repository.mapper.DetectiveRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Repository
public class DetectiveRepoImpl extends AbstractRepoimpl<Detective> implements DetectiveRepo {
    private RowMapper<Detective> rowMapper = new DetectiveRowMapper();

    public DetectiveRepoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    @Override
    public void create(Detective detective) {
        jdbcTemplate.update(
                "insert into detectives(id, create_time, modify_time, armed, badge_number, employmentStatus, `rank`,person_id) " +
                        "values(?,?,?,?,?,?,?,?)",
                detective.getId(), LocalDateTime.now(), LocalDateTime.now(), detective.getArmed(), detective.getBadgeNumber(),
                String.valueOf(detective.getEmploymentStatus()),
                String.valueOf(detective.getRank()), detective.getPerson().getId()
        );
    }

    @Override
    public Set<Detective> getALl() {
        String sql = "select d.id, d.badge_number, d.rank, d.ARMED, d.employmentStatus, d.person_id, p.user_name, " +
                "p.first_name, p.last_name, p.hiring_date FROM detectives d, persons p WHERE d.person_id = p.id;";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    @Override
    public Detective findById(Long id) {
        String sql = "select d.id, d.create_time, d.modify_time, d.version, d.armed, d.badge_number," +
                "d.employmentStatus, d.`rank`, d.person_id, p.user_name, p.first_name, p.last_name, p.hiring_date " +
                "from detectives d, persons p where d.id = ? " +
                "and d.person_id = p.id";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Detective update(Detective detective) {
        jdbcTemplate.update("UPDATE detectives d SET d.modify_time = now(), d.version = ?, d.armed = ?, " +
                        "d.employmentStatus = ?, d.`rank` = ?  WHERE d.id = ?",
                detective.getVersion(), detective.getArmed(),String.valueOf(detective.getEmploymentStatus()),
                String.valueOf(detective.getRank()), detective.getId());
        return detective;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from detectives where id =? ", id);
    }
}
