package fis.dao;

import fis.core.CaseType;
import fis.core.CriminalCase;
import fis.store.InMemoryCriminalCaseStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CriminalCaseDAO {
    private final InMemoryCriminalCaseStore criminalCaseStore = new InMemoryCriminalCaseStore();

    /**
     * add them 1 ho so vu an vao he thong
     *
     * @param criminalCase
     */
    public void addCriminalCase(CriminalCase criminalCase) {
        // open connection to db
        // Add new criminal case
        criminalCaseStore.getCriminalCaseMap().put(criminalCase.getId(), criminalCase);
    }

    /**
     * Tim tat ca ho so vu an dang luu trong he thong
     * @return tra ve danh sach chua tat ca vu an trong he thong
     */
    public List<CriminalCase> findAll() {
        return new ArrayList<>(criminalCaseStore.getCriminalCaseMap().values());
    }

    /**
     * Tim ra danh sach tat ca vu an theo kieu vu an tuong ung
     * @param type
     * @return danh sach cac vu an co kieu tuong ung
     */
    public List<CriminalCase> findByCaseType(CaseType type) {
        List<CriminalCase> list = new ArrayList<>();
        for (Map.Entry<Long, CriminalCase> e : criminalCaseStore.getCriminalCaseMap().entrySet()) {
            if (e.getValue().getType().equals(type)) {
                list.add(e.getValue());
            }
        }
        return list;
    }

    /**
     * Tim vu an trong he thong theo id
     *
     * @param id
     * @return tra ve vu an co id tuong ung neu no ton tai trong he thong
     */
    public CriminalCase findCriminalCaseById(long id) {
        if (!criminalCaseStore.getCriminalCaseMap().containsKey(id)) {
            //TODO throw exception thong bao khong ton tai ho so co id nay trong he thong
            System.err.printf("Khong ton tai ho so co id %s", id);
        }
        for (Map.Entry<Long, CriminalCase> e : criminalCaseStore.getCriminalCaseMap().entrySet()) {
            if (e.getKey() == id) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * Cap nhat thong tin cho vu an vao he thong
     *
     * @param criminalCase
     */
    public void updateCriminalCase(CriminalCase criminalCase) {
        if (findCriminalCaseById(criminalCase.getId()) == null) {
            //TODO throw exception thong bao vu an khong the update vi ko ton tai trong he thong
        }
        criminalCaseStore.getCriminalCaseMap().put(criminalCase.getId(), criminalCase);
    }

    /**
     * Xoa 1 ho so vu an dua vao so ho so
     *
     * @param id
     */
    public void deleteCriminalCase(long id) {
        //TODO...
        if (findCriminalCaseById(id) == null) {
            //TODO
            System.err.println("Khong ton tai ho so nay");
        }
        criminalCaseStore.getCriminalCaseMap().remove(id);
    }
}
