package repo.dao;

import core.CriminalCase;
import core.Detective;

import java.util.Set;

public interface ICaseDetectiveDAO {
    Set<Detective> findSetDetectiveByCaseNumber(String case_number);
    Set<CriminalCase> findSetCaseByBadgeNumber(String badge_number);
    void addCaseDetective(String case_number, String badge_number);
}
