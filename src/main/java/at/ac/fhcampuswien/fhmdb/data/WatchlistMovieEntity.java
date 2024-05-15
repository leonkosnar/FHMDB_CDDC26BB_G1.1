package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Mapper Klasse: Von Movie zu WatchlistMovieEntity (keine Listen)
// Representation of table entry - One single entry in the database
// naming convention: ...Entity
// create Table and Fields with Annotations
@DatabaseTable(tableName = "watchlistMovie")
public class WatchlistMovieEntity {
    @DatabaseField(generatedId = true) // generate ID automagically!
    private Long id;
    @DatabaseField() // compare for this!
    private String apiId;
    @DatabaseField()
    private String title;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private String genres;
    @DatabaseField()
    private int releaseYear;
    @DatabaseField()
    private String imgUrl;
    @DatabaseField()
    private int lengthInMinutes;
    @DatabaseField()
    private double rating;
    public WatchlistMovieEntity(){} // ORMLite needs no args constructor
    public WatchlistMovieEntity(Movie movie) throws DatabaseException{
        this.apiId = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.genres = genresToString(movie.getGenres());
        this.releaseYear = movie.getReleaseYear();
        this.imgUrl = movie.getImgUrl();
        this.lengthInMinutes = movie.getLengthInMinutes();
        this.rating = movie.getRating();
    }
    private String genresToString(List<Genre> genres) throws DatabaseException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            stringBuilder.append(genres.get(i));
            if (i < genres.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
    public List<Genre> getGenres() {
        return stringToList(genres).stream()
                .filter(e -> !e.equals("null"))
                .map(Genre::valueOf)
                .collect(Collectors.toList());
    }
    public List<String> stringToList(String string) {
        return new ArrayList<>(Arrays.asList(string.split(",")));
    }

    public long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }
}

