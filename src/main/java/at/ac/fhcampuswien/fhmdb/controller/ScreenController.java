package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Objects;

// helps to switch scenes
public class ScreenController {

    private static Stage stage = null;
    public static void initializeStage(Stage stage){

        ScreenController.stage=stage;

    }
    public static void switchToHomeView() {
        try {
            switchViews("home-view.fxml", "FHMDb");
        } catch (IOException e) {
            System.out.println("FXML Failure");
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
    }
    public static void switchToWatchlistView() {
        try {
            switchViews("watchlist-view.fxml", "Watchlist");
        } catch (IOException e) {
            System.out.println("FXML Failure");
            System.out.println( e.getMessage());
            e.printStackTrace();

        }
    }
    public static void switchViews(String fxmlFileName, String Title) throws IOException {
        FXMLLoader watchlistViewFxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource(fxmlFileName));
        Scene watchlistScene = new Scene(watchlistViewFxmlLoader.load(), 890, 620);
        watchlistScene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle(Title);
        stage.setScene(watchlistScene);
        stage.show();
    }
}
