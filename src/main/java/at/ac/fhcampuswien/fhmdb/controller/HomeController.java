package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Genre;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicBoolean;

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
    @FXML
    public JFXButton watchlistBtn;

    private WatchlistRepository watchlistRepository;
    private MovieAPI movieAPI;
    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    public ObservableList<Movie> allMovies;



// TODO gibt jene Person zurück, die am öftesten im mainCast der übergebenen Filme vorkommt.
    public static String getMostPopularActor(List<Movie> movies) {
        if (movies.isEmpty()) return "";

        List<String> allStrings = movies.stream()
                .flatMap(obj -> obj.getMainCast().stream())
                .toList();

        Map<String, Long> frequencyMap = allStrings.stream()
                .collect(Collectors.groupingBy(str -> str, Collectors.counting()));


        return frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

// TODO filtert auf den längsten Titel der übergebenen Filme und gibt die Anzahl der Buchstaben des Titels zurück
    public static int getLongestMovieTitle(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return 0;
        }

        return movies.stream()
                .map(Movie::getTitle)
                .filter(title -> title != null && !title.trim().isEmpty())
                .mapToInt(title -> title.trim().length())
                .max()
                .orElse(0);
    }

    // TODO gibt die Anzahl der Filme eines bestimmten Regisseurs zurück.
    public static long countMoviesFrom(List<Movie> movies, String director) {
        if (movies == null || movies.isEmpty() || director == null || director.trim().isEmpty()) {
            return 0;
        }

        return movies.stream()
                .filter(movie -> movie.getDirectors() != null)
                .flatMap(movie -> movie.getDirectors().stream())
                .filter(dir -> dir != null && dir.trim().equalsIgnoreCase(director.trim()))
                .count();
    }

    // TODO gibt jene Filme zurück, die zwischen zwei gegebenen Jahren veröffentlicht wurden.
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        if (movies == null || movies.isEmpty() || startYear > endYear) {
            return new ArrayList<>();
        }

        return movies.stream()
                .filter(movie -> {
                    int releaseYear = movie.getReleaseYear();
                    return releaseYear >= startYear && releaseYear <= endYear;
                })
                .collect(Collectors.toList());
    }
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
        initializeBehaviour();

//        movieAPI = new MovieAPI();
//        genreComboBox.setPromptText("Filter by Genre");
//        releaseYearComboBox.setPromptText("Filter by Release Year");
//        ratingComboBox.setPromptText("Filter by Rating");
//        ObservableList<Movie> allMovies;
//        try {
//            observableMovies.addAll(movieAPI.getAllMovies(MovieAPI.BASE_URL));
//            allMovies = observableMovies;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        movieListView.setItems(observableMovies);   // set data of observable list to list view
//        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked, "Add to Watchlist")); // use custom cell factory to display data
//
//
//        List<Integer> years = extractReleaseYears(allMovies);
//        releaseYearComboBox.getItems().addAll(years);
//
//
//        for (int rating = 0; rating <= 10; rating++) {
//            ratingComboBox.getItems().add(rating);
//        }
//
//        genreComboBox.getItems().addAll(Genre.values());


//        searchBtn.setOnAction(actionEvent -> {
//            updateMovieList();
//        });
//        AtomicBoolean ascendingOrder = new AtomicBoolean(true);
//
//        sortBtn.setOnAction(actionEvent -> {
//            ObservableList<Movie> sortedMovies = FXCollections.observableArrayList(movieListView.getItems());
//
//
//            ascendingOrder.set(!ascendingOrder.get());
//
//            if (ascendingOrder.get()) {
//                Collections.sort(sortedMovies, Comparator.comparing(Movie::getTitle));
//                sortBtn.setText("Sort (asc)");
//            } else {
//                Collections.sort(sortedMovies, Comparator.comparing(Movie::getTitle).reversed());
//                sortBtn.setText("Sort (desc)");
//            }
//
//            movieListView.setItems(sortedMovies);
//        });
//        watchlistBtn.setOnAction(actionEvent -> ScreenController.switchToWatchlistView());
    }

    public void initializeState() {
        movieAPI = new MovieAPI();
        try{
            observableMovies.addAll(movieAPI.getAllMovies(MovieAPI.BASE_URL));

        }catch(MovieApiException e){
            e.printStackTrace();
        }

        try {
            allMovies = observableMovies;
            watchlistRepository = new WatchlistRepository();

        } catch (DatabaseException e) {
        }
    }

    public void initializeLayout(){
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Filter by Rating");

        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked, "Add to Watchlist")); // use custom cell factory to display data


        List<Integer> years = extractReleaseYears(allMovies);
        releaseYearComboBox.getItems().addAll(years);


        for (int rating = 0; rating <= 10; rating++) {
            ratingComboBox.getItems().add(rating);
        }

        genreComboBox.getItems().addAll(Genre.values());
    }
    public void initializeBehaviour(){
        searchBtn.setOnAction(actionEvent -> {
            try {
                updateMovieList();
            } catch (MovieApiException e) {
                throw new RuntimeException(e);
            }
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
        watchlistBtn.setOnAction(actionEvent -> ScreenController.switchToWatchlistView());
    }

    private void updateMovieList() throws MovieApiException {
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


    private final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedMovie) -> {
        System.out.println("Add to Watchlist clicked on movie: " + clickedMovie);
        try {
            watchlistRepository.addToWatchlist(new WatchlistMovieEntity(clickedMovie));
        } catch (DatabaseException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            a.setHeaderText("Error");
            a.setTitle("Error");
            a.show();
        }
    };
   

}