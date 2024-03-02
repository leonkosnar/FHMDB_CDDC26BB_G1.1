package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    public enum genreEnum {
        ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY,
        CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR,
        MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR,
        WESTERN
    }
    private List<genreEnum> genres;

    // TODO add more properties here

    public Movie(String title, String description, List<genreEnum> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Star Wars: Episode I – The Phantom Menace", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode II – Attack of the Clones", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode III – Revenge of the Sith", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode IV – A New Hope", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode V – The Empire Strikes Back", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode VI – Return of the Jedi", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode VII – The Force Awakens", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode VIII – The Last Jedi", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));
        movies.add(new Movie("Star Wars: Episode IX – The Rise of Skywalker", "", new ArrayList<>(Arrays.asList(genreEnum.SCIENCE_FICTION, genreEnum.FAMILY))));

        return movies;
    }
}
