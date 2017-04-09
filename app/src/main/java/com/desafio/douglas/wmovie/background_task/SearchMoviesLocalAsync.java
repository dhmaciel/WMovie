package com.desafio.douglas.wmovie.background_task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.service.DetailMovieService;
import com.desafio.douglas.wmovie.service_impl.DetailMovieServiceImpl;

import java.util.List;

/**
 * Created by worlo on 08/04/2017.
 */

public class SearchMoviesLocalAsync extends AsyncTask<Void, Void, List<DetailMovie>> {

    private final String TAG = SaveOrDeleteMovieDetailAsync.class.getName();
    private Context context;
    private ISearchMovieswLocalAsync iSearchMovieswLocalAsync;
    private ProgressDialog mProgressDialog;

    public SearchMoviesLocalAsync(Context context, ISearchMovieswLocalAsync iSearchMovieswLocalAsync) {
        this.context = context;
        this.iSearchMovieswLocalAsync = iSearchMovieswLocalAsync;
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
    protected List<DetailMovie> doInBackground(Void... voids) {
        try{
            DetailMovieService service = new DetailMovieServiceImpl(context);
            return service.listar("data_alteracao", false);
        }catch (Exception ex){
            Log.e(TAG, "Erro ao listar filmes", ex);
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<DetailMovie> detailMovieList) {
        super.onPostExecute(detailMovieList);
        if(detailMovieList != null){
            iSearchMovieswLocalAsync.onSuccess(detailMovieList);
        }else{
            iSearchMovieswLocalAsync.onError(context.getString(R.string.toast_main_listagem_vazia));
        }
        mProgressDialog.dismiss();
    }
}
