package fis.service;

import fis.core.CaseType;
import fis.core.CriminalCase;

import java.sql.SQLException;
import java.util.List;

public interface ICriminalCaseService {

    /**
     * Tao moi 1 ho so vu an
     * @param criminalCase
     * @return tra ve ho so vu an sau khi duoc tao moi thanh cong vao he thong
     */
    CriminalCase createCriminalCase(CriminalCase criminalCase) throws SQLException;

    /**
     * Hien thi tat ca ho so vu an dang luu trong he thong
     */
    void displayAll();

    /**
     * Tim ho so vu an theo so ho so
     * @param id
     * @return tra ve ho so vu an tim duoc theo so tuong ung
     */
    CriminalCase findById(long id);

    /**
     * Tim ra danh sach cac ho so vu an co kieu vu an tuong ung
     * @param type kieu vu an ( co 4 kieu )
     * @return tra ve danh sach vu an co kieu tuong ung tim duoc
     */
    List<CriminalCase> findByCaseType(CaseType type);

    /**
     * Xoa ho so 1 vu an theo so ho so
     * @param id
     */
    void deleteById(long id);



}
