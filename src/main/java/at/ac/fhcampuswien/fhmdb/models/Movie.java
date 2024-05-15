package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;

import java.util.List;
import java.util.ArrayList;

public class Movie {

    private String id;
    private String title;
    private String description;
    public List<Genre> genres;
    private int releaseYear;
    private String imgUrl;
    private int lengthInMinutes;
    private ArrayList<String> directors;
    private ArrayList<String> writers;
    private ArrayList<String> mainCast;
    private double rating;


    public Movie(WatchlistMovieEntity watchlistMovieEntity){
        this.id = watchlistMovieEntity.getApiId();
        this.title = watchlistMovieEntity.getTitle();
        this.description = watchlistMovieEntity.getDescription();
        this.genres = watchlistMovieEntity.getGenres();
        this.releaseYear = watchlistMovieEntity.getReleaseYear();
        this.imgUrl = watchlistMovieEntity.getImgUrl();
        this.lengthInMinutes = watchlistMovieEntity.getLengthInMinutes();
        this.rating = watchlistMovieEntity.getRating();
    }


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
    public List<Genre> getGenres(){
        return genres;
    };

    public String getGenresAsString() {
        if(genres == null) return "";
        return genres.stream().map(Enum::name).reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }
    public String getDirectorsAsString() {
        if(directors == null) return "";
        return directors.stream().reduce((a, b) -> a + ", " + b).orElse("n/a");
    }
    public String getWritersAsString() {
        if(writers == null) return "";
        return writers.stream().reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }
    public String getMainCastAsString() {
        if(mainCast == null) return "";
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

