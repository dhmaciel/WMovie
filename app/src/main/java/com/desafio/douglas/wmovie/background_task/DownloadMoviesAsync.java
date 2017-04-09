package com.desafio.douglas.wmovie.background_task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.desafio.douglas.wmovie.model.Search;
import com.desafio.douglas.wmovie.model.SearchMovie;
import com.desafio.douglas.wmovie.web_service.ApiClient;
import com.desafio.douglas.wmovie.web_service.ApiInterface;
import com.desafio.douglas.wmovie.web_service.SearchMoviesWS;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by worlo on 05/04/2017.
 */

public class DownloadMoviesAsync extends AsyncTask<String, Void, Void> implements IDownloadMoviesCallBack {

    private final String TAG = DownloadMoviesAsync.class.getName();
    private IDownloadMoviesCallBack iDownloadMoviesCallBackWeakReference;
    private Context context;
    private int pagina;

    public DownloadMoviesAsync(IDownloadMoviesCallBack iDownloadMoviesCallBackWeakReference,
                               Context context, int page) {
        this.iDownloadMoviesCallBackWeakReference = iDownloadMoviesCallBackWeakReference;
        this.context = context;
        this.pagina = page;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        changeVisibilityProgress(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(String... params) {
        String titulo = params[0];

        if(titulo != null && !titulo.equals("")){
            ApiInterface apiService =
                    ApiClient.getClient(context).create(ApiInterface.class);

            new SearchMoviesWS(apiService).pesquisarFilmesPorPagina(pagina, titulo,  this);
        }

        return null;
    }

    @Override
    public void onSuccess(List<Search> searchList) {
        changeVisibilityProgress(View.GONE);
        iDownloadMoviesCallBackWeakReference.onSuccess(searchList);
    }

    @Override
    public void onError(String msgError) {
        msgError = (msgError == null) ? "Não foi possível carregar Listagem de filmes" : msgError;
        Log.e(TAG, msgError);
        changeVisibilityProgress(View.GONE);
        iDownloadMoviesCallBackWeakReference.onError(msgError);
    }

    @Override
    public void changeVisibilityProgress(int visibility) {
        iDownloadMoviesCallBackWeakReference.changeVisibilityProgress(visibility);
    }
}
