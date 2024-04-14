package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final VBox layout = new VBox(title, detail);

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle() + " : " + movie.getRating() + " - " + movie.getLengthInMinutes() + "min" + " | " + movie.getReleaseYear());

            String movieDetails = (movie.getDescription() != null ? movie.getDescription() : "No description available")
                    + "\n\nGenres: " + movie.getGenresAsString()
                    + "\n\nDirectors: " + movie.getDirectorsAsString()
                    + "\n\nWriters: " + movie.getWritersAsString()
                    + "\n\nCast: " + movie.getMainCastAsString();

            detail.setText(movieDetails);
            setGraphic(layout);


            // color scheme
            title.getStyleClass().add("move-cell-title");
            layout.setBackground(new Background(new BackgroundFill(Color.web("lightgray"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

