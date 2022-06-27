package repo.dao;

import core.CriminalCase;

public interface ICriminalCaseDAO extends IDao<CriminalCase> {
    CriminalCase findByNumber(String number);
}
