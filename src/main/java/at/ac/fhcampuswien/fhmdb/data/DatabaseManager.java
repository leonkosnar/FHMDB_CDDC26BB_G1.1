package at.ac.fhcampuswien.fhmdb.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;


public class DatabaseManager {
    private static final String DB_URL = "jdbc:h2:file:./db/watchlistmoviesdb";
    private static final String username = "user";
    private static final String password = "password";
    private static ConnectionSource connectionSource;
    /*
    Data Access Object:
    provides crud, translates to and from SQL (provided by ORMLite)
    Instantiate with 2 generic types, here WatchlistMovieEntity and Long (for ID)
    */
    private Dao<WatchlistMovieEntity, Long> dao;
    private static DatabaseManager instance;
    // private Constructor
    private DatabaseManager() {
        try {
            createConnectionSource();
            this.dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // use to retrieve DatabaseManager: instantiate with private constructor if !exists
    public static DatabaseManager getDatabase() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        // if instance already exists just return it
        return instance;
    }
    private void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);
    }
    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }
    public Dao<WatchlistMovieEntity, Long> getDao() {
        return dao;
    }
    private void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }



}