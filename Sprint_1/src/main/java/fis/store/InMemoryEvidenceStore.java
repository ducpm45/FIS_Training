package fis.store;

import fis.core.Evidence;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryEvidenceStore {
    private final Map<Long, Evidence> evidenceMap;

    public InMemoryEvidenceStore() {
        this.evidenceMap = createEvidenceMap();
    }

    private Map<Long, Evidence> createEvidenceMap() {
        Map<Long, Evidence> map = new HashMap<>();
        map.put(1L,
                new Evidence.Builder()
                        .setId(1)
                        .setNumber("CC1")
                        .setItemName("Dao")
                        .setNotes("Khong co dau van tay")
                        .setArchived(false)
                        .build()
        );
        map.put(2L,
                new Evidence.Builder()
                        .setId(2)
                        .setNumber("CC2")
                        .setItemName("Sung")
                        .setNotes("Co dau van tay 2 nguoi")
                        .setArchived(false)
                        .build()
        );
        map.put(3L,
                new Evidence.Builder()
                        .setId(3)
                        .setNumber("CC3")
                        .setItemName("Toc")
                        .setNotes("Chua xet nghiem ADN")
                        .setArchived(false)
                        .build()
        );
        map.put(4L,
                new Evidence.Builder()
                        .setId(4)
                        .setNumber("CC4")
                        .setItemName("Loi khai hang xom")
                        .setNotes("Khong nhin thay gi")
                        .setArchived(false)
                        .build()
        );

        return map;
    }

    public Map<Long, Evidence> getEvidenceMap() {
        return evidenceMap;
    }
}
