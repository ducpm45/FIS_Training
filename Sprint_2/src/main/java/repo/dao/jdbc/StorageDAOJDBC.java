package repo.dao.jdbc;

import core.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repo.connectpool.DataSource;
import repo.dao.IStorageDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StorageDAOJDBC implements IStorageDAO {
    private static final Logger log = LoggerFactory.getLogger(CriminalCaseDAOJDBC.class);
    @Override
    public void create(Storage storage) {
        String query = "INSERT INTO sprint_2.storages\n" +
                "VALUE (?, ?, ?, ?, ?, ?);";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, storage.getId());
            ps.setInt(2, storage.getVersion());
            ps.setString(3, localDateTimeToString(LocalDateTime.now()));
            ps.setString(4, localDateTimeToString(LocalDateTime.now()));
            ps.setString(5, storage.getName());
            ps.setString(6, storage.getLocation());
            if(ps.executeUpdate() < 0) {
                log.error("Them " + storage.getName() + " khong thanh cong!");
            } else {
                log.info("Them " + storage.getName() + " thanh cong!");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public Optional<List<Storage>> getAll() {
        String query = "select * from storages;";
        List<Storage> storages = new ArrayList<>();
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Storage storage = new Storage.Builder()
                        .setId(rs.getLong(1))
                        .setVersion(rs.getInt(2))
                        .setCreatedAt(stringToLocalDateTime(rs.getString(3)))
                        .setModifiedAt(stringToLocalDateTime(rs.getString(4)))
                        .setName(rs.getString(5))
                        .setLocation(rs.getString(6))
                        .build();
                storages.add(storage);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return Optional.of(storages);
    }

    @Override
    public void update(Storage storage) {
        String query = "update storages\n" +
                "set\n" +
                "    id = ?,\n" +
                "    version = ?,\n" +
                "    modified_time = ?,\n" +
                "    location = ?\n" +
                "where name = ?;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, storage.getId());
            ps.setInt(2, storage.getVersion());
            ps.setString(3, localDateTimeToString(LocalDateTime.now()));
            ps.setString(4, storage.getLocation());
            ps.setString(5, storage.getName());
            if(ps.executeUpdate() < 0) {
                log.error("Update khong thanh cong");
            } else {
                log.info("Update thanh cong");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void delete(Storage storage) {
        if(findByName(storage.getName()) == null) {
            log.info("Khong ton tai kho luu tru nay trong he thong");
        }
        String query = "delete from storages where name = ?;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, storage.getName());

            if(ps.executeUpdate() < 0) {
                log.error("Xoa " + storage.getName() + " khong thanh cong!");
            } else {
                log.info("Xoa " + storage.getName() + " thanh cong!");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    public void deleteAll() {
        String query = "delete from storages;";
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public Storage findByName(String name) {
        String query = "select * from storages where name = ?;";
        Storage.Builder storage = new Storage.Builder();
        try(Connection con = DataSource.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                storage.setId(rs.getLong(1));
                storage.setVersion(rs.getInt(2));
                storage.setCreatedAt(stringToLocalDateTime(rs.getString(3)));
                storage.setModifiedAt(stringToLocalDateTime(rs.getString(4)));
                storage.setName(rs.getString(5));
                storage.setLocation(rs.getString(6));
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return storage.build();
    }
}
