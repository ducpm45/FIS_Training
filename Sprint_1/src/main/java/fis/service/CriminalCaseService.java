package fis.service;

import fis.core.CaseType;
import fis.core.CriminalCase;
import fis.dao.CriminalCaseDAO;

import java.util.List;

public class CriminalCaseService implements ICriminalCaseService{
    private final CriminalCaseDAO dao = new CriminalCaseDAO();
    @Override
    public CriminalCase createCriminalCase(CriminalCase criminalCase) {
        //TODO validate data criminalCase
        // Add to db
        dao.addCriminalCase(criminalCase);
        return criminalCase;
    }

    @Override
    public void displayAll() {
        List<CriminalCase> list = dao.findAll();
        list.forEach(System.out::println);
    }

    @Override
    public CriminalCase findById(long id) {
        return dao.findCriminalCaseById(id);
    }

    @Override
    public List<CriminalCase> findByCaseType(CaseType type) {
        return dao.findByCaseType(type);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteCriminalCase(id);
    }
}
