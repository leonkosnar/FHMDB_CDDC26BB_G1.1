package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import at.ac.fhcampuswien.fhmdb.Genre;

public class Movie {

    private String id;
    private String title;
    public static ArrayList<Genre> genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private ArrayList<String> directors;
    private ArrayList<String> writers;
    private ArrayList<String> mainCast;
    private double rating;

    public Movie(String id, String title,  ArrayList<Genre> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, ArrayList<String> directors, ArrayList<String> writers, ArrayList<String> mainCast, int rating) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.description = description;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public Movie(){

    }
    public String getId() {return id;}
    public String getTitle() {return title;}
    public int getReleaseYear() {return releaseYear;}
    public String getDescription() {return description;}
    public String getImgUrl() {return imgUrl;}
    public int getLengthInMinutes() {return lengthInMinutes;}
    public double getRating() {return rating;}
    public ArrayList<String> getMainCast() {return mainCast;}
    public ArrayList<String> getDirectors() {return directors;}
    public ArrayList<Genre> getGenres(){
        return genres;
    };

    public String getGenresAsString() {
//        return genres.stream().map(Enum::name).reduce((a, b) -> a + ", " + b).orElse("No Genre");
        return "";
    }
    public String getDirectorsAsString() {
        return directors.stream().reduce((a, b) -> a + ", " + b).orElse("n/a");
    }
    public String getWritersAsString() {
        return writers.stream().reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }
    public String getMainCastAsString() {
        return mainCast.stream().reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public void setWriters(ArrayList<String> writers) {
        this.writers = writers;
    }

    public void setMainCast(ArrayList<String> mainCast) {
        this.mainCast = mainCast;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}

