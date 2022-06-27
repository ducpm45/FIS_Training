package fis.store;

import fis.core.Detective;
import fis.core.EmploymentStatus;
import fis.core.Rank;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryDetectiveStore {
    private final Map<Long, Detective> detectiveMap;

    public InMemoryDetectiveStore() {
        detectiveMap = createDetectiveMap();
    }

    private Map<Long, Detective> createDetectiveMap() {
        Map<Long, Detective> map = new HashMap<>();
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
                        .setId(2)
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
                        .setId(3)
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
                        .setId(4)
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

    public Map<Long, Detective> getDetectiveMap() {
        return detectiveMap;
    }
}
