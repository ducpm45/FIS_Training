package service;

import java.sql.SQLException;

public interface IService<T> {
    /**
     * Them moi doi tuong T vao he thong
     * @param item
     */
    void addNew(T item) throws SQLException;

    /**
     * Hien thi tat ca doi tuong dang duoc luu tru trong he thong
     */
    void displayAll();

    /**
     * Cap nhat thong tin mot doi tuong da co trong he thong
     */
    void update(T item);

    /**
     * Xoa doi tuong T khoi he thong
     */
    void delete(T item);
}
