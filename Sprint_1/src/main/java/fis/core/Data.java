package fis.core;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<Long, CriminalCase> criminalCaseMap;
    private Map<Long, Evidence> evidenceMap;
    private Map<Long, Detective> detectiveMap;

    public Data() {
        criminalCaseMap = createCriminalCaseMap();
        evidenceMap = createEvidenceMap();
        detectiveMap = createDetectiveMap();
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

    private Map<Long, Detective> createDetectiveMap() {
        Map<Long ,Detective> map = new HashMap<>();
        map.put(1L,
                new Detective.Builder()
                        .setId(1)
                        .setUserName("detective1")
                        .setFirstName("Pham")
                        .setLastName("Minh Duc")
                        .setPassword("phamminhduc@")
                        .setBadgeNumber("007")
                        .setRank(Rank.INSPECTOR)
                        .setStatus(EmploymentStatus.ACTIVE)
                        .build()
        );
        map.put(2L,
                new Detective.Builder()
                        .setId(3)
                        .setUserName("detective2")
                        .setFirstName("Nguyen")
                        .setLastName("Hoang Hai")
                        .setPassword("hoanghai#")
                        .setBadgeNumber("006")
                        .setRank(Rank.CHIEF_INSPECTOR)
                        .setStatus(EmploymentStatus.RETIRED)
                        .build()
        );
        map.put(3L,
                new Detective.Builder()
                        .setId(4)
                        .setUserName("detective3")
                        .setFirstName("Tran")
                        .setLastName("Van Ngon")
                        .setPassword("ngonnv$")
                        .setBadgeNumber("002")
                        .setRank(Rank.TRAINEE)
                        .setStatus(EmploymentStatus.VACATION)
                        .build()
        );
        map.put(4L,
                new Detective.Builder()
                        .setUserName("detective4")
                        .setFirstName("Tran")
                        .setLastName("Hao Nam")
                        .setPassword("namca@")
                        .setBadgeNumber("001")
                        .setRank(Rank.SENIOR)
                        .setStatus(EmploymentStatus.SUSPENDED)
                        .build()
        );

        return map;
    }

    private Map<Long, Evidence> createEvidenceMap() {
        Map<Long, Evidence> map = new HashMap<>();

        map.put(1L,
                new Evidence.Builder()
                        .setNumber("CC1")
                        .setItemName("Dao")
                        .setNotes("Khong co dau van tay")
                        .setArchived(false)
                        .build()
        );
        map.put(2L,
                new Evidence.Builder()
                        .setNumber("CC2")
                        .setItemName("Sung")
                        .setNotes("Co dau van tay 2 nguoi")
                        .setArchived(false)
                        .build()
        );
        map.put(3L,
                new Evidence.Builder()
                        .setNumber("CC3")
                        .setItemName("Toc")
                        .setNotes("Chua xet nghiem ADN")
                        .setArchived(false)
                        .build()
        );
        map.put(4L,
                new Evidence.Builder()
                        .setNumber("CC4")
                        .setItemName("Loi khai hang xom")
                        .setNotes("Khong nhin thay gi")
                        .setArchived(false)
                        .build()
        );
        return map;
    }

    public Map<Long, CriminalCase> getCriminalCaseMap() {
        return criminalCaseMap;
    }

    public Map<Long, Evidence> getEvidenceMap() {
        return evidenceMap;
    }

    public Map<Long, Detective> getDetectiveMap() {
        return detectiveMap;
    }
}
