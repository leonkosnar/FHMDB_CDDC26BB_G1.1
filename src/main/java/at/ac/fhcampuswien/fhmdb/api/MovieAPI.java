package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.List;


public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at";
    private static final String MOVIES_ENDPOINT = "/movies";

    private final OkHttpClient httpClient;
    private final Gson gson;

    public MovieAPI() {
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<Movie> getAllMovies() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + MOVIES_ENDPOINT)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody body = response.body();
            if (body != null) {
                String json = body.string();
                return gson.fromJson(json, List.class);
            }
        }

        return null;
    }

    public List<Movie> searchMovies(String query, String genre) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + MOVIES_ENDPOINT).newBuilder();
        urlBuilder.addQueryParameter("query", query);
        urlBuilder.addQueryParameter("genre", genre);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody body = response.body();
            if (body != null) {
                String json = body.string();
                return gson.fromJson(json, List.class);
            }
        }

        return null;
    }
}
