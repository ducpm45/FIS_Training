package repo.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    /**
     * add vao db
     * @param item
     */
    void create(T item) throws SQLException;

    /**
     * lay ra toan bo phan tu trong db
     *
     * @return
     */
    Optional<List<T>> getAll() throws SQLException;

    /**
     * cap nhat 1 phan tu trong db
     * @param item
     */
    void update(T item);

    /**
     * xoa 1 phan tu trong db
     * @param item
     */
    void delete(T item);

    default String localDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    default LocalDateTime stringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
