package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WatchlistController implements Initializable {
    @FXML
    public JFXButton homeViewButton;
    @FXML
    public JFXListView<Movie> movieListView;
    public List<Movie> allMovies;
    public ObservableList<Movie> observableWatchlistMovies = FXCollections.observableArrayList();
    private WatchlistRepository watchlistRepository;
    // new Constructor provided:
    public WatchlistController(){
        // retrieve WatchlistRepository Object here
        try {
            watchlistRepository = new WatchlistRepository();
        } catch(DatabaseException e) {
            e.printStackTrace();
        }
        // Subscribe here if necessary
        System.out.println("Constructing WatchlistController: " + this);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
        initializeBehaviour();
    }
    public void initializeState(){
        try {
            allMovies = watchlistRepository.getDao().queryForAll().stream().map(Movie::new).collect(Collectors.toList());
        } catch (SQLException | DatabaseException e) {
            System.out.println("watchlistController line 55");
            System.out.println(e.getMessage() + e.getCause());
        }
        observableWatchlistMovies.clear();
        observableWatchlistMovies.addAll(allMovies);
    }
    public void initializeLayout(){
        movieListView.setItems(observableWatchlistMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(onRemoveFromWatchlistClicked, "Remove from Watchlist"));
    }
    public void initializeBehaviour(){
        homeViewButton.setOnAction(actionEvent -> ScreenController.switchToHomeView());
    }
    private final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedMovie) ->
    {
        System.out.println("Remove from Watchlist clicked on movie: " + clickedMovie.getTitle());
        try {
            watchlistRepository.removeFromWatchlist(new WatchlistMovieEntity(clickedMovie));
            ScreenController.switchToWatchlistView();
        } catch (DatabaseException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Failed to remove movie.", ButtonType.OK);
            a.setHeaderText("Error");
            a.setTitle("Error");
            a.show();
        }
    };


}
