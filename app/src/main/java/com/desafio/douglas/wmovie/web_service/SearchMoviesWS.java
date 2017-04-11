package com.desafio.douglas.wmovie.web_service;

import com.desafio.douglas.wmovie.background_task.IDownloadMoviesCallBack;
import com.desafio.douglas.wmovie.model.SearchMovie;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by worlo on 05/04/2017.
 */

public class SearchMoviesWS {

    private final String TAG = SearchMoviesWS.class.getName();
    private ApiInterface apiService;

    public SearchMoviesWS(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public void pesquisarFilmesPorPagina(int pagina, String titulo ,final
    IDownloadMoviesCallBack callBack){

        Map<String, Integer> page = new HashMap<>();
        page.put("page", pagina);

        Call<SearchMovie> call = apiService.getMoviesFilter(titulo, page);

        call.enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(Call<SearchMovie> call, Response<SearchMovie> response) {
                final SearchMovie searchMovie = response.body();
                if(searchMovie != null){
                    callBack.onSuccess(searchMovie.getSearch());
                }
            }

            @Override
            public void onFailure(Call<SearchMovie> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });

    }
}
