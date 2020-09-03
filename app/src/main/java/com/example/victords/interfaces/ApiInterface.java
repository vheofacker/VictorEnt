package com.example.victords.interfaces;

import com.example.victords.classes.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);
//https://api.themoviedb.org/3/movie/upcoming?api_key=e213ce02cbad41b6e3cc4353cbef97d5&language=pt-BR&page=1
}
