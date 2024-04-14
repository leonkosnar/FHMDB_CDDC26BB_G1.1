package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.lang.reflect.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at";
    private static final String MOVIES_ENDPOINT = "/movies";

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final Type movieListType;


    public MovieAPI() {
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
        this.movieListType = new TypeToken<List<Movie>>(){}.getType();
    }

    public List<Movie> getAllMovies() throws IOException {
        List<Movie> movies = new ArrayList<>();
        // Movie(String id, String title, ArrayList<String> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, ArrayList<String> directors, ArrayList<String> writers, ArrayList<String> mainCast, float rating)
        movies.add(new Movie("abc123", "Test", new ArrayList<>(Arrays.asList("ACTION")), 2020, "desc test test 123", "url", 10, new ArrayList<>(Arrays.asList("Leonardo Davinci")), new ArrayList<>(Arrays.asList("megatron")), new ArrayList<>(Arrays.asList("megatron")), 9.9));
        return movies;
        /*
        Request request = new Request.Builder()
                .url(BASE_URL + MOVIES_ENDPOINT)
                .header("User-Agent", "http.agent")
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody body = response.body();
            if (body != null) {
                String json = body.string();
                System.out.println("JSON:");
                System.out.println(json);
                System.out.println("ARRAY:");
                System.out.println(gson.fromJson(json, Movie.class));
                return List.of(gson.fromJson(json, Movie[].class));
            }
        }
        catch (Exception ignored){}

        return null;
         */
    }

    public List<Movie> searchMovies(String query, String genre) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + MOVIES_ENDPOINT).newBuilder();
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
