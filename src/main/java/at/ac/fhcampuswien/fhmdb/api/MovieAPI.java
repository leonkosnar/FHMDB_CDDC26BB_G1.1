package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieAdapter;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.lang.reflect.Type;

import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.List;

public class MovieAPI {
    public static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";


    public MovieAPI() {

    }

    public List<Movie> getAllMovies(String endpoint) throws IOException {

        Type movieListType = new TypeToken<List<Movie>>(){}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Movie.class, new MovieAdapter())
                .create();

        OkHttpClient httpClient = new OkHttpClient();


        Request request = new Request.Builder()
                .url(endpoint)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            try (ResponseBody body = response.body()) {
                if (body != null) {
                    String json = body.string();

                    return gson.fromJson(json, movieListType);

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception ignored){ }

      return null;
    }


    public List<Movie> getMoviesByQuery(String endpoint, String query, int releaseYear, double ratingFrom){

        String baseUrl = endpoint + "?";

        Type movieListType = new TypeToken<List<Movie>>(){}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Movie.class, new MovieAdapter())
                .create();

        OkHttpClient httpClient = new OkHttpClient();



        if (query != null) baseUrl += "query=" + query + "&";
        if (releaseYear > 0) baseUrl += "releaseYear=" + String.valueOf(releaseYear) + "&";
        if(ratingFrom > 0) baseUrl += "ratingFrom=" + String.valueOf(ratingFrom) + "&";
        // TODO: query build for genre


        Request request = new Request.Builder()
                .url(baseUrl)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            try (ResponseBody body = response.body()) {
                if (body != null) {
                    String json = body.string();

                    return gson.fromJson(json, movieListType);

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception ignored){ }

        return null;
    }

    public List<Movie> searchMovies(String endpoint, String query, String genre) throws IOException {

        Type movieListType = new TypeToken<List<Movie>>(){}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Movie.class, new MovieAdapter())
                .create();

        OkHttpClient httpClient = new OkHttpClient();



        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("query", query);
        urlBuilder.addQueryParameter("genre", genre);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "http.agent")
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody body = response.body();
            if (body != null) {
                String json = body.string();
                return gson.fromJson(json, movieListType);
            }
        }catch (Exception ignored){}

        return null;
    }
}
