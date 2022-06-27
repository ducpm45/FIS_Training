package repo.dao.jdbc;

import core.CriminalCase;
import core.Detective;
import core.enums.CaseStatus;
import core.enums.CaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repo.connectpool.DataSource;
import repo.dao.ICriminalCaseDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CriminalCaseDAOJDBC implements ICriminalCaseDAO {
    private static final Logger log = LoggerFactory.getLogger(CriminalCaseDAOJDBC.class);
    private static final CaseDetectiveDAOJDBC cddj = new CaseDetectiveDAOJDBC();
    private static final DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();

    @Override
    public void create(CriminalCase criminalCase) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO sprint_2.criminal_case_list\n" +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = DataSource.getConnection();
            assert con != null;
            ps = con.prepareStatement(query);
            ps.setLong(1, criminalCase.getId());
            ps.setString(2, localDateTimeToString(criminalCase.getCreatedAt()));
            ps.setString(3, localDateTimeToString(criminalCase.getModifiedAt()));
            ps.setString(4, criminalCase.getNumber());
            ps.setString(5, String.valueOf(criminalCase.getType()));
            ps.setString(6, criminalCase.getShortDescription());
            ps.setString(7, String.valueOf(criminalCase.getStatus()));
            ps.setString(8, criminalCase.getNotes());
            ps.setString(9, criminalCase.getLeadInvestigator().getBadgeNumber());
            if (ps.executeUpdate() > 0) {
                log.info("Them moi " + criminalCase.getNumber() + " thanh cong");
            } else {
                log.error("Them moi " + criminalCase.getNumber() + " khong thanh cong");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            assert ps != null;
            ps.close();
            con.close();
        }
    }

    @Override
    public Optional<List<CriminalCase>> getAll() throws SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select * from criminal_case_list;";
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        try {
            con = DataSource.getConnection();
            assert con != null;
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                CriminalCase.Builder criminalCase = new CriminalCase.Builder()
                        .setId(rs.getLong(1))
                        .setCreatedAt(stringToLocalDateTime(rs.getString(2)))
                        .setModifiedAt(stringToLocalDateTime(rs.getString(3)))
                        .setNumber(rs.getString(4))
                        .setType(CaseType.valueOf(rs.getString(5)))
                        .setShortDescription(rs.getString(6))
                        .setStatus(CaseStatus.valueOf(rs.getString(7)))
                        .setNotes(rs.getString(8));
                Detective detectiveLead = ddj.findByBadgeNumber(rs.getString(9));
                criminalCase.setLeadInvestigator(detectiveLead);
                criminalCaseList.add(criminalCase.build());
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            assert rs != null;
            rs.close();
            st.close();
            con.close();
        }
        return Optional.of(criminalCaseList);
    }

    @Override
    public CriminalCase findByNumber(String case_number) {
        String query = "select * from criminal_case_list where number = ?;";
        CriminalCase.Builder criminalCase = new CriminalCase.Builder();
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, case_number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                criminalCase.setId(rs.getLong(1));
                criminalCase.setCreatedAt(stringToLocalDateTime(rs.getString(2)));
                criminalCase.setModifiedAt(stringToLocalDateTime(rs.getString(3)));
                criminalCase.setNumber(rs.getString(4));
                criminalCase.setType(CaseType.valueOf(rs.getString(5)));
                criminalCase.setShortDescription(rs.getString(6));
                criminalCase.setStatus(CaseStatus.valueOf(rs.getString(7)));
                criminalCase.setNotes(rs.getString(8));
                Detective detectiveLead = ddj.findByBadgeNumber(rs.getString(9));
                criminalCase.setLeadInvestigator(detectiveLead);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return criminalCase.build();
    }

    @Override
    public void update(CriminalCase criminalCase) {
        String query = "update criminal_case_list\n" +
                "set\n" +
                "    id = ?,\n" +
                "    modified_date = ?,\n" +
                "    case_type = ?,\n" +
                "    description = ?,\n" +
                "    status = ?,\n" +
                "    notes = ?,\n" +
                "    lead_investigator = ?\n" +
                "where number = ?";
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, criminalCase.getId());
            ps.setString(2, localDateTimeToString(LocalDateTime.now()));
            ps.setString(3, String.valueOf(criminalCase.getType()));
            ps.setString(4, criminalCase.getShortDescription());
            ps.setString(5, String.valueOf(criminalCase.getStatus()));
            ps.setString(6, criminalCase.getNotes());
            ps.setString(7, criminalCase.getLeadInvestigator().getBadgeNumber());
            ps.setString(8, criminalCase.getNumber());
            if (ps.executeUpdate() > 0) {
                log.info("Update thanh cong");
            } else {
                log.error("Update that bai");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void delete(CriminalCase criminalCase) {
        if (findByNumber(criminalCase.getNumber()) == null) {
            log.info("Khong ton tai ho so nay trong he thong");
        }
        String query = "delete from criminal_case_list where number = ?;";
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, criminalCase.getNumber());

            if (ps.executeUpdate() < 0) {
                log.error("Xoa " + criminalCase.getNumber() + " khong thanh cong!");
            } else {
                log.info("Xoa " + criminalCase.getNumber() + " thanh cong!");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    public void deleteAll() {
        String query = "delete from criminal_case_list;";
        try (Connection con = DataSource.getConnection()) {
            assert con != null;
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }
}
