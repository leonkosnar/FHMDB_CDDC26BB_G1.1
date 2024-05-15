package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.DisableSSLValidation;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.utils.MovieAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieAPI {

    public static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";

    public MovieAPI() {
    }

    public List<Movie> getAllMovies(String endpoint) throws IOException {
        DisableSSLValidation.disable();
        Type movieListType = new TypeToken<List<Movie>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        DisableSSLValidation.disable();
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("HTTP error code: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        List<Movie> movies = gson.fromJson(response.toString(), movieListType);

        return movies;
    }

    public List<Movie> getMoviesByQuery(String endpoint, String query, int releaseYear, double ratingFrom, String genre) {
        DisableSSLValidation.disable();
        String baseUrl = endpoint + "?";

        Type movieListType = new TypeToken<List<Movie>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Movie.class, new MovieAdapter())
                .create();

        OkHttpClient httpClient = new OkHttpClient();

        if (query != null) baseUrl += "query=" + query + "&";
        if (releaseYear > 0) baseUrl += "releaseYear=" + String.valueOf(releaseYear) + "&";
        if (ratingFrom > 0) baseUrl += "ratingFrom=" + String.valueOf(ratingFrom) + "&";
        if (genre != null) baseUrl += "genre=" + genre + "&";

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ignored) {
        }

        return null;
    }
}