package com.desafio.douglas.wmovie.background_task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.web_service.ApiClient;
import com.desafio.douglas.wmovie.web_service.ApiInterface;
import com.desafio.douglas.wmovie.web_service.MovieDetailWS;

/**
 * Created by worlo on 06/04/2017.
 */

public class DownloadDetailMovieAsync extends AsyncTask<String, Void, Void> implements IDownloadDetailMovieCallBack{

    private final String TAG = DownloadDetailMovieAsync.class.getName();
    private IDownloadDetailMovieCallBack iDownloadDetailMovieCallBack;
    private Context context;
    private ProgressDialog mProgressDialog;

    public DownloadDetailMovieAsync(IDownloadDetailMovieCallBack iDownloadDetailMovieCallBack, Context context) {
        this.iDownloadDetailMovieCallBack = iDownloadDetailMovieCallBack;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(context.getResources().getString(R.string.carregando));
        mProgressDialog.show();
    }

    @Override
    protected Void doInBackground(String... strings) {

        String id = strings[0];

        if(id != null && !id.equals("")){
            ApiInterface apiService =
                    ApiClient.getClient(context).create(ApiInterface.class);

            new MovieDetailWS(apiService).pesquisarDetalheFilmePorId(id, this);
        }

        return null;
    }

    @Override
    public void onSuccess(DetailMovie detailMovieList) {
        mProgressDialog.dismiss();
        iDownloadDetailMovieCallBack.onSuccess(detailMovieList);
    }

    @Override
    public void onError(String msgError) {
        mProgressDialog.dismiss();
        Log.e(TAG, msgError);
        iDownloadDetailMovieCallBack.onError(msgError);
    }
}
