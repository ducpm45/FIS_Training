package repo.dao.jdbc;

import core.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StorageDAOJDBCTest {
    private static final StorageDAOJDBC sdj = new StorageDAOJDBC();
    @BeforeEach
    void beforeEach() {
        Storage s1 = new Storage.Builder()
                .setId(1)
                .setVersion(1)
                .setName("S001")
                .setLocation("Khu 1")
                .build();
        Storage s2 = new Storage.Builder()
                .setId(2)
                .setVersion(2)
                .setName("S002")
                .setLocation("Khu 2")
                .build();
        Storage s3 = new Storage.Builder()
                .setId(3)
                .setVersion(3)
                .setName("S003")
                .setLocation("Khu 3")
                .build();
        Storage s4 = new Storage.Builder()
                .setId(4)
                .setVersion(4)
                .setName("S004")
                .setLocation("Khu 4")
                .build();
        Storage s5 = new Storage.Builder()
                .setId(5)
                .setVersion(5)
                .setName("S005")
                .setLocation("Khu 5")
                .build();

        sdj.create(s1);
        sdj.create(s2);
        sdj.create(s3);
        sdj.create(s4);
        sdj.create(s5);
    }
    @Test
    void create() {
        Storage s6 = new Storage.Builder()
                .setId(6)
                .setVersion(6)
                .setName("S006")
                .setLocation("Khu 6")
                .build();
        sdj.create(s6);
    }

    @Test
    void getAll() {
        Assertions.assertEquals(5, sdj.getAll().get().size());
    }

    @Test
    void update() {
        Storage s6 = new Storage.Builder()
                .setId(6)
                .setVersion(6)
                .setName("S001")
                .setLocation("Khu 6")
                .build();
        sdj.create(s6);

    }
    @Test
    void findByName() {
        Storage storage = sdj.findByName("S004");
        Assertions.assertEquals(4, storage.getId());
        Assertions.assertEquals(4, storage.getVersion());
        Assertions.assertEquals("Khu 4", storage.getLocation());
    }

    @Test
    void delete() {
        Storage s3 = new Storage.Builder()
                .setId(3)
                .setVersion(3)
                .setName("S003")
                .setLocation("Khu 3")
                .build();
        sdj.delete(s3);
        Assertions.assertEquals(4, sdj.getAll().get().size());
    }
    @AfterEach
    void afterEach() {
        sdj.deleteAll();
    }
}