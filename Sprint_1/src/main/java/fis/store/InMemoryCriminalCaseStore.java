package fis.store;

import fis.core.CaseStatus;
import fis.core.CaseType;
import fis.core.CriminalCase;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryCriminalCaseStore {
    private final Map<Long, CriminalCase> criminalCaseMap;

    public InMemoryCriminalCaseStore() {
        this.criminalCaseMap = createCriminalCaseMap();
    }

    private Map<Long, CriminalCase> createCriminalCaseMap() {
        Map<Long, CriminalCase> map = new HashMap<>();
        map.put(1L,
                new CriminalCase.Builder()
                        .setId(1)
                        .setNumber("HS1")
                        .setType(CaseType.FELONY)
                        .setStatus(CaseStatus.DISMISSED)
                        .setNotes("ABC")
                        .build()
        );
        map.put(2L,
                new CriminalCase.Builder()
                        .setId(2)
                        .setNumber("HS2")
                        .setType(CaseType.INFRACTION)
                        .setStatus(CaseStatus.IN_COURT)
                        .setNotes("AAA")
                        .build()
        );
        map.put(3L,
                new CriminalCase.Builder()
                        .setId(3)
                        .setNumber("HS3")
                        .setType(CaseType.MISDEMEANOR)
                        .setStatus(CaseStatus.SUBMITTED)
                        .setNotes("BBB")
                        .build()
        );
        map.put(4L,
                new CriminalCase.Builder()
                        .setId(4)
                        .setNumber("HS4")
                        .setType(CaseType.UNCATEGORIZED)
                        .setStatus(CaseStatus.UNDER_INVESTIGATION)
                        .setNotes("CCC")
                        .build()
        );
        return map;
    }

    public Map<Long, CriminalCase> getCriminalCaseMap() {
        return criminalCaseMap;
    }
}
