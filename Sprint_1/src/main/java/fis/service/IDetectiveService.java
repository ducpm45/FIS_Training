package fis.service;

import fis.core.Detective;
import fis.core.EmploymentStatus;
import fis.core.Rank;

import java.util.List;

public interface IDetectiveService {
    /**
     * Tao moi mot detective
     * @param detective
     * @return tra ve doi tuong detective sau khi da tao moi trong he thong thanh cong
     */
    Detective createDetective(Detective detective);

    /**
     * Tim tat ca tham tu dang duoc luu tru trong he thong
     * @return tra ve list chua tat ca tham tu
     */
     List<Detective> findAll();

    /**
     * Tim detective theo so huy hieu
     * @param badgeNumber
     * @return tra ve detective co so hieu tuong ung voi so huy hieu
     */
    Detective findByBadgeNumber(String badgeNumber);

    /**
     * Tim ra danh sach detective o 1 rank nao do
     * @param rank
     * @return Tra ve list detective co rank tuong ung param
     */
    List<Detective> findByRank(Rank rank);

    /**
     * Tim detective dua theo status
     * @param status
     * @return tra ve list detective tim duoc dua vao status
     */
     List<Detective> findByEmployeeStatus(EmploymentStatus status);

    /**
     * Update thong tin cho 1 detective
     * @param detective
     * @return tra ve detective duoc update thong tin
     */
    Detective updateDetective(Detective detective);

    /**
     * Xoa 1 detective khoi he thong dua vao so huy hieu
     * @param badgeNumber
     */
    void deleteDetective(long id);
}
