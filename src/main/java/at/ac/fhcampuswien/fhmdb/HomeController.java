package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXComboBox releaseYearComboBox;

    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies;
    private MovieAPI movieAPI;

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    /*
    protected SortedList<Movie> getMoviesWithAppliedFilters(ObservableList<Movie> movies, String sort, String comboboxValue, String searchText){
        return sort.equals("Sort (asc)")
                ? new SortedList<Movie>(filterByTitle(filterByGenre(movies, comboboxValue), searchText), Comparator.comparing(Movie::getTitle).reversed())
                : new SortedList<Movie>(filterByTitle(filterByGenre(movies, comboboxValue), searchText), Comparator.comparing(Movie::getTitle));
    }

    //Filter Movies By Genre
    protected ObservableList<Movie> filterByGenre(ObservableList<Movie> movies, String comboboxValue){
        return comboboxValue.isEmpty() ? movies : movies.filtered(movie -> movie.getGenresAsString().contains(comboboxValue));
    }

    //Filter Movies By Title
    protected ObservableList<Movie> filterByTitle(ObservableList<Movie> movies, String searchText){
        return movies.filtered(movie -> movie.getTitle().toLowerCase().contains(searchText.toLowerCase()));
    }
    */

    // TODO gibt jene Person zurück, die am öftesten im mainCast der übergebenen Filme vorkommt.
    public static String getMostPopularActor(List<Movie> movies) {
        // Validate the input list of movies
        if (movies == null || movies.isEmpty()) {
            return ""; // Return an empty string if the list is null or empty
        }

        // Create a map to store each actor's name and their occurrences
        Map<String, Long> actorNameToOccurrencesMap = new HashMap<>();

        // Iterate through each movie in the list
        for (Movie movie : movies) {
            // Check if the movie's main cast is not empty
            if (movie.getMainCast() != null && !movie.getMainCast().isEmpty()) {
                // Iterate through each actor in the main cast
                for (String actor : movie.getMainCast()) {
                    // Increment the occurrence count of the actor in the map
                    actorNameToOccurrencesMap.put(actor, actorNameToOccurrencesMap.getOrDefault(actor, 0L) + 1);
                }
            }
        }

        // Find the actor with the maximum occurrence count
        Optional<Map.Entry<String, Long>> mostPopularActorEntry = actorNameToOccurrencesMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        // Return the name of the most popular actor, or an empty string if no actor is found
        return mostPopularActorEntry.map(Map.Entry::getKey).orElse("");
    }




    // TODO filtert auf den längsten Titel der übergebenen Filme und gibt die Anzahl der Buchstaben des Titels zurück
    public static int getLongestMovieTitle(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        for (Movie movie : movies) {
            String title = movie.getTitle();
            if (title != null && !title.trim().isEmpty()) {
                maxLength = Math.max(maxLength, title.trim().length());
            }
        }
        return maxLength;
    }



    // TODO gibt die Anzahl der Filme eines bestimmten Regisseurs zurück.
    public static long countMoviesFrom(List<Movie> movies, String director) {
        if (movies == null || movies.isEmpty() || director == null || director.trim().isEmpty()) {
            return 0;
        }

        long count = 0;
        for (Movie movie : movies) {
            List<String> directors = movie.getDirectors();
            if (directors != null && !directors.isEmpty()) {
                for (String dir : directors) {
                    if (dir != null && dir.trim().equalsIgnoreCase(director.trim())) {
                        count++;
                        break; // Break from inner loop once director is found
                    }
                }
            }
        }
        return count;
    }

    // TODO gibt jene Filme zurück, die zwischen zwei gegebenen Jahren veröffentlicht wurden.
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        if (movies == null || movies.isEmpty() || startYear > endYear) {
            return new ArrayList<>();
        }

        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            int releaseYear = movie.getReleaseYear();
            if (releaseYear >= startYear && releaseYear <= endYear) {
                result.add(movie);
            }
        }
        return result;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieAPI = new MovieAPI();
        try {
            observableMovies.addAll(movieAPI.getAllMovies(MovieAPI.BASE_URL));         // add dummy data to observable list
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data


        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Filter by Rating");

        // add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
//        genreComboBox.getItems().addAll(
//                Arrays.asList(Movie.genreEnum.values())
//        );

        // DONE add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Genre Filter
        genreComboBox.setOnAction(actionEvent -> {
            /*
            String genre = genreComboBox.getSelectionModel().isEmpty() ? "" : genreComboBox.getValue().toString();
            movieListView.setItems(getMoviesWithAppliedFilters(observableMovies, sortBtn.getText(), genre, searchField.getText()));
            */
        });

        // Sort
        sortBtn.setOnAction(actionEvent -> {
            /*
            String genre = genreComboBox.getSelectionModel().isEmpty() ? "" : genreComboBox.getValue().toString();
            movieListView.setItems(getMoviesWithAppliedFilters(observableMovies, sortBtn.getText(), genre, searchField.getText()));
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
            } else {
                sortBtn.setText("Sort (asc)");
            }
            */
        });

        // Search (Title + Genre + Sort)
        searchBtn.setOnAction(actionEvent -> {
            /*
            String genre = genreComboBox.getSelectionModel().isEmpty() ? "" : genreComboBox.getValue().toString();
            movieListView.setItems(getMoviesWithAppliedFilters(observableMovies, sortBtn.getText(), genre, searchField.getText()));
            */
        });
    }
}