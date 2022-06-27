package repo.dao.jdbc;

import core.CriminalCase;
import core.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repo.connectpool.DataSource;
import repo.dao.ICaseDetectiveDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CaseDetectiveDAOJDBC implements ICaseDetectiveDAO {
    private static final Logger log = LoggerFactory.getLogger(CriminalCaseDAOJDBC.class);
    @Override
    public Set<Detective> findSetDetectiveByCaseNumber(String case_number) {
        DetectiveDAOJDBC ddj = new DetectiveDAOJDBC();
        Set<Detective> detectiveSet = new HashSet<>();
        List<String> badgeNumberList = getListBadgeNumberByCaseNumber(case_number);
        for (String s : badgeNumberList) {
            detectiveSet.add(ddj.findByBadgeNumber(s));
        }
        return detectiveSet;
    }

    @Override
    public Set<CriminalCase> findSetCaseByBadgeNumber(String badge_number) {
        CriminalCaseDAOJDBC ccdj = new CriminalCaseDAOJDBC();
        Set<CriminalCase> criminalCaseSet = new HashSet<>();
        List<String> caseNumberList = getListCaseNumberByBadgeNumber(badge_number);
        for(String s : caseNumberList) {
            criminalCaseSet.add(ccdj.findByNumber(s));
        }
        return criminalCaseSet;
    }

    @Override
    public void addCaseDetective(String case_number, String badge_number) {
        String query = "INSERT INTO case_detective VALUE(?, ?);";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, case_number);
            ps.setString(2, badge_number);
            if(ps.executeUpdate() < 0) {
                log.error("Them vao case_detective khong thanh cong!");
            } else {
                log.info("Them vao case_detective thanh cong");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    public List<String> getListBadgeNumberByCaseNumber(String case_number) {
        List<String> listBadgeNumber = new ArrayList<>();
        String query = "select detective_badge_number from case_detective where case_number = ?;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, case_number);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                listBadgeNumber.add(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return listBadgeNumber;
    }

    public List<String> getListCaseNumberByBadgeNumber(String badge_number) {
        List<String> listCaseNumber = new ArrayList<>();
        String query = "select case_number from case_detective where detective_badge_number = ?;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, badge_number);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                listCaseNumber.add(rs.getString(1));
            }

        } catch (SQLException e) {
            log.error(e.toString());
        }
        return listCaseNumber;
    }
}
