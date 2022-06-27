package service;

import core.CriminalCase;
import core.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repo.dao.jdbc.CaseDetectiveDAOJDBC;
import repo.dao.jdbc.CriminalCaseDAOJDBC;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class CriminalCaseService implements ICriminalCaseService {
    private static final Logger log = LoggerFactory.getLogger(CriminalCaseService.class);
    private static final CriminalCaseDAOJDBC caseDAO = new CriminalCaseDAOJDBC();
    private static final CaseDetectiveDAOJDBC caseDetecDAO = new CaseDetectiveDAOJDBC();

    @Override
    public void addNew(CriminalCase criminalCase) throws SQLException {
        //TODO validate criminalCase...

        caseDAO.create(criminalCase);
        Set<Detective> detectiveSet = criminalCase.getAssigned();
        detectiveSet.forEach(d -> {
            caseDetecDAO.addCaseDetective(criminalCase.getNumber(), d.getBadgeNumber());
        });
    }

    @Override
    public void displayAll() {
        List<CriminalCase> criminalCaseList = caseDAO.getAll().orElseThrow();
        if(null == criminalCaseList) {
            log.error("CriminalCase list is null");
        }
        criminalCaseList.forEach(c -> {
            Set<Detective> detectiveSet = caseDetecDAO.findSetDetectiveByCaseNumber(c.getNumber());
            //TODO...display data to....
        });
    }

    @Override
    public void update(CriminalCase criminalCase) {
        if(caseDAO.findByNumber(criminalCase.getNumber()) == null) {
            log.error("Khong ton tai trong he thong!");
        }
        // update on criminal_case_list table
        caseDAO.update(criminalCase);
        // update on case_detective table
        Set<Detective> detectiveSet = criminalCase.getAssigned();
        List<String> badgeNumberList = caseDetecDAO.getListBadgeNumberByCaseNumber(criminalCase.getNumber());
        detectiveSet.forEach(d -> {
            if(!badgeNumberList.contains(d.getBadgeNumber())) {
                caseDetecDAO.addCaseDetective(criminalCase.getNumber(), d.getBadgeNumber());
            }
        });
    }

    @Override
    public void delete(CriminalCase criminalCase) {
        if(caseDAO.findByNumber(criminalCase.getNumber()) == null) {
            log.error("Khong ton tai trong he thong!");
        }

        caseDAO.delete(criminalCase);
    }
}
