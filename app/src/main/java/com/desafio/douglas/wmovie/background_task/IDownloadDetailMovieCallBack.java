package com.desafio.douglas.wmovie.background_task;

import com.desafio.douglas.wmovie.model.DetailMovie;

/**
 * Created by worlo on 06/04/2017.
 */

public interface IDownloadDetailMovieCallBack {

    void onSuccess(DetailMovie detailMovieList);
    void onError(String msgError);
}
