package repo.dao;

import core.Detective;

public interface IDetectiveDAO extends IDao<Detective> {
    Detective findByBadgeNumber(String badgeNumber);
}
