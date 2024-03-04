package at.ac.fhcampuswien.fhmdb;

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
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    private SortedList getMoviesWithAppliedFilters(){
        return sortBtn.getText().equals("Sort (asc)") ? new SortedList(getFiltered(), Comparator.comparing(Movie::getTitle).reversed()) : new SortedList(getFiltered(), Comparator.comparing(Movie::getTitle));
    }
    private ObservableList<Movie> getFiltered(){
        if(genreComboBox.getSelectionModel().isEmpty()) return observableMovies;
        return observableMovies.filtered(movie -> movie.getGenresAsString().contains(genreComboBox.getValue().toString()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(
                Arrays.asList(Movie.genreEnum.values())
        );

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Genre Filter
        genreComboBox.setOnAction(actionEvent -> {
            movieListView.setItems(getMoviesWithAppliedFilters());
        });

        // Sort
        sortBtn.setOnAction(actionEvent -> {
            movieListView.setItems(getMoviesWithAppliedFilters());
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
            } else {
                sortBtn.setText("Sort (asc)");
            }
        });

        // Search (Title + Genre + Sort)

    }
}