package com.desafio.douglas.wmovie.web_service;

import com.desafio.douglas.wmovie.background_task.IDownloadDetailMovieCallBack;
import com.desafio.douglas.wmovie.model.DetailMovie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by worlo on 06/04/2017.
 */

public class MovieDetailWS {

    private final String TAG = MovieDetailWS.class.getName();
    private ApiInterface apiService;

    public MovieDetailWS(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public void pesquisarDetalheFilmePorId(String id, final
    IDownloadDetailMovieCallBack callBack){

        Call<DetailMovie> call = apiService.getMovieDetail(id);

        call.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                final DetailMovie detailMovie = response.body();
                if(detailMovie != null){
                    callBack.onSuccess(detailMovie);
                }
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
}
