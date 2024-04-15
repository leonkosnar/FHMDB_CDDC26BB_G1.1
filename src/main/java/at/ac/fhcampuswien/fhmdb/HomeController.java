package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXComboBox<Integer> releaseYearComboBox;

    @FXML
    public JFXComboBox<Integer> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    private MovieAPI movieAPI;
    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieAPI = new MovieAPI();
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Filter by Rating");
        ObservableList<Movie> allMovies;
        try {
            observableMovies.addAll(movieAPI.getAllMovies(MovieAPI.BASE_URL));
            allMovies = observableMovies;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data


        List<Integer> years = extractReleaseYears(allMovies);
        releaseYearComboBox.getItems().addAll(years);


        for (int rating = 0; rating <= 10; rating++) {
            ratingComboBox.getItems().add(rating);
        }

        genreComboBox.getItems().addAll(Genre.values());


        searchBtn.setOnAction(actionEvent -> {
            updateMovieList();
        });
        AtomicBoolean ascendingOrder = new AtomicBoolean(true);

        sortBtn.setOnAction(actionEvent -> {
            ObservableList<Movie> sortedMovies = FXCollections.observableArrayList(movieListView.getItems());


            ascendingOrder.set(!ascendingOrder.get());

            if (ascendingOrder.get()) {
                Collections.sort(sortedMovies, Comparator.comparing(Movie::getTitle));
                sortBtn.setText("Sort (asc)");
            } else {
                Collections.sort(sortedMovies, Comparator.comparing(Movie::getTitle).reversed());
                sortBtn.setText("Sort (desc)");
            }

            movieListView.setItems(sortedMovies);
        });

    }

    private void updateMovieList() {
        String query = searchField.getText();
        int releaseYear = releaseYearComboBox.getValue() != null ? releaseYearComboBox.getValue() : 0;
        double ratingFrom = ratingComboBox.getValue() != null ? ratingComboBox.getValue() : 0.0;
        Genre genre = genreComboBox.getValue() != null ? (Genre) genreComboBox.getValue() : null;


        observableMovies.setAll(movieAPI.getMoviesByQuery(MovieAPI.BASE_URL, query, releaseYear, ratingFrom, genre != null ? genre.name() : null));
    }

    private List<Integer> extractReleaseYears(List<Movie> movies) {
        Set<Integer> years = new HashSet<>();
        for (Movie movie : movies) {
            years.add(movie.getReleaseYear());
        }

        ArrayList<Integer> yearsList = new ArrayList<>(years);
        Collections.sort(yearsList, Collections.reverseOrder());


        return yearsList;
    }

}