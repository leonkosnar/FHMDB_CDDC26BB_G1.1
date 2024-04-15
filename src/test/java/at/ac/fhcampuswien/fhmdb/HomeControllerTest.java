/*package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeControllerTest {


    @Test
    void sortByTitleAscending() {
        HomeController hc = new HomeController();
        List<Movie> movies = new ArrayList<>();
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

        movies.add(new Movie("A", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ACTION))));
        movies.add(new Movie("B", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ADVENTURE))));
        observableMovies.addAll(movies);

        observableMovies = hc.getMoviesWithAppliedFilters(observableMovies, "Sort (asc)", "", "");
        assertEquals("B", observableMovies.get(0).getTitle());
    }

    @Test
    void sortByTitleDescending() {
        HomeController hc = new HomeController();
        List<Movie> movies = new ArrayList<>();
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

        movies.add(new Movie("A", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ACTION))));
        movies.add(new Movie("B", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ADVENTURE))));
        observableMovies.addAll(movies);

        observableMovies = hc.getMoviesWithAppliedFilters(observableMovies, "Sort (desc)", "", "");
        assertEquals("A", observableMovies.get(0).getTitle());
    }

    @Test
    void filterByGenre() {
        HomeController hc = new HomeController();
        List<Movie> movies = new ArrayList<>();
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

        movies.add(new Movie("A", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ACTION))));
        movies.add(new Movie("B", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ADVENTURE))));
        observableMovies.addAll(movies);

        observableMovies = hc.filterByGenre(observableMovies, Movie.genreEnum.ADVENTURE.name());
        assertEquals("ADVENTURE", observableMovies.get(0).getGenresAsString());
    }

    @Test
    void searchMoviesByTitle() {
        HomeController hc = new HomeController();
        List<Movie> movies = new ArrayList<>();
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

        movies.add(new Movie("A", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ACTION))));
        movies.add(new Movie("B", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.ADVENTURE))));
        movies.add(new Movie("C", "",
                new ArrayList<>(Arrays.asList(Movie.genreEnum.DRAMA))));
        observableMovies.addAll(movies);

        observableMovies = hc.getMoviesWithAppliedFilters(observableMovies, "Sort (asc)", "", "B");
        assertEquals("B", observableMovies.get(0).getTitle());
    }
}*/