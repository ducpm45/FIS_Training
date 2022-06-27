package repo.dao.jdbc;

import core.Detective;
import core.enums.EmploymentStatus;
import core.enums.Rank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repo.connectpool.DataSource;
import repo.dao.IDetectiveDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetectiveDAOJDBC implements IDetectiveDAO {
    private static final Logger log = LoggerFactory.getLogger(CriminalCaseDAOJDBC.class);
    @Override
    public void create(Detective detective) {
        String query = "INSERT INTO sprint_2.detective_list\n" +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, detective.getId());
            ps.setString(2, localDateTimeToString(LocalDateTime.now()));
            ps.setString(3, detective.getUserName());
            ps.setString(4, detective.getFirstName());
            ps.setString(5, detective.getLastName());
            ps.setString(6, detective.getPassword());
            ps.setString(7, localDateTimeToString(LocalDateTime.now()));
            ps.setString(8, detective.getBadgeNumber());
            ps.setString(9, String.valueOf(detective.getRank()));
            ps.setBoolean(10, detective.getArmed());
            ps.setString(11, String.valueOf(detective.getStatus()));
            if (ps.executeUpdate() > 0) {
                log.info("Them moi " + detective.getBadgeNumber() + " thanh cong");
            } else {
                log.error("Them moi " + detective.getBadgeNumber() + " khong thanh cong");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public Optional<List<Detective>> getAll() {
        String query = "select * from detective_list;";
        List<Detective> detectiveList = new ArrayList<>();
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Detective detective = new Detective.Builder()
                        .setId(rs.getLong(1))
                        .setModifiedAt(stringToLocalDateTime(rs.getString(2)))
                        .setUserName(rs.getString(3))
                        .setFirstName(rs.getString(4))
                        .setLastName(rs.getString(5))
                        .setPassword(rs.getString(6))
                        .setHiringDate(stringToLocalDateTime(rs.getString(7)))
                        .setBadgeNumber(rs.getString(8))
                        .setRank(Rank.valueOf(rs.getString(9)))
                        .setArmed(rs.getBoolean(10))
                        .setStatus(EmploymentStatus.valueOf(rs.getString(11)))
                        .build();
                detectiveList.add(detective);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return Optional.of(detectiveList);
    }

    @Override
    public void update(Detective detective) {
        String query = "update detective_list\n" +
                "set\n" +
                "    id = ?,\n" +
                "    modified_date = ?,\n" +
                "    first_name = ?,\n" +
                "    last_name = ?,\n" +
                "    password = ?,\n" +
                "    `rank` = ?,\n" +
                "    armed = ?,\n" +
                "    employee_status = ?\n" +
                "where badge_number = ?;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, detective.getId());
            ps.setString(2, localDateTimeToString(LocalDateTime.now()));
            ps.setString(3, detective.getFirstName());
            ps.setString(4, detective.getLastName());
            ps.setString(5, detective.getPassword());
            ps.setString(6, String.valueOf(detective.getRank()));
            ps.setBoolean(7, detective.getArmed());
            ps.setString(8, String.valueOf(detective.getStatus()));
            ps.setString(9, detective.getBadgeNumber());
            if(ps.executeUpdate() > 0) {
                log.info("Update thanh cong");
            } else {
                log.error("Update that bai");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void delete(Detective detective) {
        if(findByBadgeNumber(detective.getBadgeNumber()) == null) {
            log.error("Khong ton tai tham tu nay trong he thong");
        }
        String query = "delete from detective_list where badge_number = ?;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, detective.getBadgeNumber());
            if(ps.executeUpdate() < 0) {
                log.error("Xoa " + detective.getBadgeNumber() + " khong thanh cong!");
            } else {
                log.info("Xoa " + detective.getBadgeNumber() + " thanh cong!");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public Detective findByBadgeNumber(String badgeNumber) {
        String query = "select * from detective_list where badge_number = ?;";
        Detective.Builder detective = new Detective.Builder();
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, badgeNumber);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                detective.setId(rs.getLong(1));
                detective.setModifiedAt(stringToLocalDateTime(rs.getString(2)));
                detective.setUserName(rs.getString(3));
                detective.setFirstName(rs.getString(4));
                detective.setLastName(rs.getString(5));
                detective.setPassword(rs.getString(6));
                detective.setHiringDate(stringToLocalDateTime(rs.getString(7)));
                detective.setBadgeNumber(rs.getString(8));
                detective.setRank(Rank.valueOf(rs.getString(9)));
                detective.setArmed(rs.getBoolean(10));
                detective.setStatus(EmploymentStatus.valueOf(rs.getString(11)));
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return detective.build();
    }

    public void deleteAll() {
        String query = "delete from detective_list;";
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }
}
