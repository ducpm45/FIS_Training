package fis.service;

import fis.core.Evidence;
import fis.dao.EvidenceDAO;

public class EvidenceService implements IEvidenceService {

    @Override
    public Evidence createEvidence(Evidence evidence) {
        //TODO validate data
        EvidenceDAO dao = new EvidenceDAO();
        dao.addEvidence(evidence);
        return evidence;
    }

    @Override
    public Evidence findEvidenceById(long id) {
        EvidenceDAO dao = new EvidenceDAO();

        return dao.findById(id);
    }

    @Override
    public Evidence updateEvidence(Evidence evidence) {
        // TODO validate data
        EvidenceDAO dao = new EvidenceDAO();
        dao.updateEvidence(evidence);
        return evidence;
    }

    @Override
    public void deleteEvidence(long id) {
        EvidenceDAO dao = new EvidenceDAO();
        dao.deleteEvidence(id);
    }
}
