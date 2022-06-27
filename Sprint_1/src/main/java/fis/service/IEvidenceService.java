package fis.service;

import fis.core.Evidence;

public interface IEvidenceService {

    /**
     * Tao 1 chung cu moi
     * @param evidence
     * @return tra ve chung cu da tao sau khi thanh cong add vao kho luu tru
     */
    Evidence createEvidence(Evidence evidence);

    /**
     * Xem chung cu co name tuong ung
     * @param id
     * @return tra ve chung cu tim duoc
     */
    Evidence findEvidenceById(long id);

    /**
     * Cap nhat thong tin cho evidence
     * @param evidence
     * @return tra ve evidence sau khi thanh cong cap nhat thong tin
     */
    Evidence updateEvidence(Evidence evidence);

    /**
     * Xoa 1 evidence khoi kho luu tru
     */
    void deleteEvidence(long id);
}
