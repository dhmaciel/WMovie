package com.desafio.douglas.wmovie.web_service;

import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.model.SearchMovie;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by worlo on 05/04/2017.
 */

public interface ApiInterface {

    @GET("?type=movie")
    Call<SearchMovie> getMoviesFilter(@Query("s") String title, @QueryMap Map<String, Integer> page);

    @GET("?")
    Call<DetailMovie> getMovieDetail(@Query("i") String id);
}
