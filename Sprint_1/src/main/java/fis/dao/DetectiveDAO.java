package fis.dao;

import fis.core.Detective;
import fis.store.InMemoryDetectiveStore;

import java.util.ArrayList;
import java.util.List;

public class DetectiveDAO {
    private final InMemoryDetectiveStore detectiveStore = new InMemoryDetectiveStore();
    /**
     * Them moi 1 detective vao he thong
     *
     * @param detective
     */
    void addDetective(Detective detective) {
        // open connection to db
        detectiveStore.getDetectiveMap().put(detective.getId(), detective);
    }

    /**
     * Tim ra tat ca detective dang duoc luu tru trong he thong
     *
     * @return tra ve list tat ca detective tim duoc
     */
    List<Detective> findAll() {
        //TODO...

        return new ArrayList<>(detectiveStore.getDetectiveMap().values());
    }

    /**
     * Cap nhat thong tin cho 1 detective
     *
     * @param detective
     */
    void updateDetective(Detective detective) {
        //TODO kiem tra detective da ton tai trong he thong hay chua
        detectiveStore.getDetectiveMap().put(detective.getId(), detective);
    }

    /**
     * Xoa 1 detective khoi he thong dua vao so huy hieu
     *
     * @param id
     */
    void deleteDetective(long id) {
        //TODO kiem tra detective ton tai trong he thong hay ko
        detectiveStore.getDetectiveMap().remove(id);

    }


}

