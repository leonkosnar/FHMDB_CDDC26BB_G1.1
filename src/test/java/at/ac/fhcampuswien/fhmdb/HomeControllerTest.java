package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {


    @Test
    void sortByTitleAscending() {
        // Arrange
        List<Movie> allMovies = Movie.initializeMovies();

        // Act
        Collections.sort(allMovies, Comparator.comparing(Movie::getTitle));

        // Assert
        assertEquals("Back to the Future", allMovies.get(0).getTitle(), "First movie should be 'Back to the Future'");
        // Add more assertions as needed
    }

    @Test
    void sortByTitleDescending() {
        // Arrange
        List<Movie> allMovies = Movie.initializeMovies();

        // Act
        Collections.sort(allMovies, Comparator.comparing(Movie::getTitle).reversed());

        // Assert
        assertEquals("The Silence of the Lambs", allMovies.get(0).getTitle(), "First movie should be 'The Silence of the Lambs'");
        // Add more assertions as needed
    }

    @Test
    void sortByGenre() {
        // Arrange
        List<Movie> allMovies = Movie.initializeMovies();

        // Act
        Collections.sort(allMovies, Comparator.comparing(movie -> movie.getGenresAsString()));

        // Assert
        assertEquals("Star Wars: Episode I – The Phantom Menace", allMovies.get(0).getTitle(), "First movie should be 'Star Wars: Episode I – The Phantom Menace'");
        // Add more assertions as needed
    }

    @Test
    void searchMoviesByTitle() {
        // Arrange
        List<Movie> allMovies = Movie.initializeMovies();
        String searchTerm = "Future";

        // Act
        List<Movie> searchResult = allMovies.stream()
                .filter(movie -> movie.getTitle().contains(searchTerm))
                .toList();

        // Assert
        assertEquals(1, searchResult.size(), "Expected 1 movie containing 'Future' in the title");
        assertEquals("Back to the Future", searchResult.get(0).getTitle());
    }


}