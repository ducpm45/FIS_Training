package repo.dao;

import core.Storage;

public interface IStorageDAO extends IDao<Storage> {
    Storage findByName(String name);
}
