package fis.dao;

import fis.core.Evidence;
import fis.store.InMemoryEvidenceStore;

import java.util.Map;

public class EvidenceDAO {
    private final InMemoryEvidenceStore evidenceStore = new InMemoryEvidenceStore();

    /**
     * Them 1 evidence vao kho luu tru
     *
     * @param evidence
     */
    public void addEvidence(Evidence evidence) {
        //TODO...
        evidenceStore.getEvidenceMap().put(evidence.getId(), evidence);
    }

    /**
     * Tim chung cu dua theo so hieu tuong ung
     *
     * @param id
     * @return tra ve chung cu tim duoc
     */
    public Evidence findById(long id) {
        //TODO...
        for (Map.Entry<Long, Evidence> e : evidenceStore.getEvidenceMap().entrySet()) {
            if (e.getKey().equals(id)) {
                return e.getValue();
            }
        }
        // Neu ko tim duoc, throw exception thong bao
        return null;
    }

    /**
     * cap nhat thong tin chung cu
     *
     * @param evidence
     */
    public void updateEvidence(Evidence evidence) {
        //TODO...
        evidenceStore.getEvidenceMap().put(evidence.getId(), evidence);
    }

    /**
     * Xoa chung cu tu trong kho luu tru
     */
    public void deleteEvidence(long id) {
        //TODO... kiem tra evidence co id tuong ung co ton tai trong he thong, neu co thi xoa, neu ko
        //throw exception thong bao
        if(findById(id) == null) {
            //throw.........
        }
        evidenceStore.getEvidenceMap().remove(id);
    }
}
