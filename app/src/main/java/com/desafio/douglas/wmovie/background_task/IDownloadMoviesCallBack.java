package com.desafio.douglas.wmovie.background_task;

import com.desafio.douglas.wmovie.model.Search;

import java.util.List;

/**
 * Created by worlo on 05/04/2017.
 */

public interface IDownloadMoviesCallBack {

    void onSuccess(List<Search> searchList);
    void onError(String msgError);
    void changeVisibilityProgress(int visibility);
}
