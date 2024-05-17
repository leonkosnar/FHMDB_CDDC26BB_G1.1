package at.ac.fhcampuswien.fhmdb.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MovieApiException extends Exception{
    public MovieApiException() {
        super();
        Alert a = new Alert(Alert.AlertType.ERROR, "Es ist ein Fehler aufgetreten", ButtonType.OK);
        a.setHeaderText("Error");
        a.setTitle("Error");
        a.show();
    }
    public MovieApiException(String message) {
        super(message);
        Alert a = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        a.setHeaderText("Error");
        a.setTitle("Error");
        a.show();
    }
    public MovieApiException(Throwable cause){
        super(cause);
        Alert a = new Alert(Alert.AlertType.ERROR, "Es ist ein Fehler aufgetreten", ButtonType.OK);
        a.setHeaderText("Error");
        a.setTitle("Error");
        a.show();
    }
    public MovieApiException(String message, Throwable cause) {
        super(message, cause);
        Alert a = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        a.setHeaderText("Error");
        a.setTitle("Error");
        a.show();
    }
}
