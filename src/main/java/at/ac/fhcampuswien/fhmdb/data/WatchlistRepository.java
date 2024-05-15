package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class WatchlistRepository {
    private Dao<WatchlistMovieEntity, Long> dao;
    private final static String CONNECTION_ERROR_MESSAGE = "Failed to connect to database.";
    private static final String MOVIE_ALREADY_IN_WATCHLIST_MESSAGE = "The selected movie is already in your watchlist.";
    public Dao<WatchlistMovieEntity, Long> getDao() throws DatabaseException {
        return dao;
    }
    public WatchlistRepository() throws DatabaseException{
        this.dao = DatabaseManager.getDatabase().getDao();
    }
    public void removeFromWatchlist(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException {
        try {
            if(dao!=null) {
                this.dao.delete(dao.queryForEq("title", watchlistMovieEntity.getTitle()));
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE, e);
        }
    }
    public void addToWatchlist(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException{
        try{
            if(dao!=null) {
                if(!dao.queryForEq("title", watchlistMovieEntity.getTitle()).isEmpty()){
                    throw new DatabaseException(MOVIE_ALREADY_IN_WATCHLIST_MESSAGE);
                }
                this.dao.createIfNotExists(watchlistMovieEntity);
            }
        }catch (SQLException | IllegalArgumentException e){
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE, e);
        }
    }
    public List<WatchlistMovieEntity> getAll() throws DatabaseException {
        try {
            if(dao!=null) {
                return dao.queryForAll();
            } else {
                return Collections.emptyList();
            }
        } catch (SQLException | IllegalArgumentException e){
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE, e);
        }
    }
}
