package com.desafio.douglas.wmovie.background_task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.activity.MovieDetailActivity;
import com.desafio.douglas.wmovie.exception.RegraNegocioException;
import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.service.DetailMovieService;
import com.desafio.douglas.wmovie.service_impl.DetailMovieServiceImpl;

/**
 * Created by worlo on 07/04/2017.
 */

public class SaveOrDeleteMovieDetailAsync extends AsyncTask<DetailMovie, Void, Boolean> {

    private final String TAG = SaveOrDeleteMovieDetailAsync.class.getName();
    private Context context;
    private ProgressDialog mProgressDialog;
    private ICallBack callBack;
    private MovieDetailActivity.Mode currMode;

    public SaveOrDeleteMovieDetailAsync(Context context, ICallBack iCallBack, MovieDetailActivity.Mode mode) {
        this.context = context;
        this.callBack = iCallBack;
        this.currMode = mode;
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
    protected Boolean doInBackground(DetailMovie... detailMovies) {

        final DetailMovie detailMovie = detailMovies[0];
        if(detailMovie != null){
            try {
                DetailMovieService service = new DetailMovieServiceImpl(context);
                if(currMode == MovieDetailActivity.Mode.Insert){
                    return service.salvarOuAtualizar(detailMovie);
                }else{
                    return service.deletar(detailMovie);
                }

            } catch (RegraNegocioException e) {
                Log.e(TAG, "Erro ao salvar detalhes do filme", e);
            }
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean isSave) {
        super.onPostExecute(isSave);
        mProgressDialog.dismiss();
        if(isSave){
            callBack.onSuccess();
        }else {
            callBack.onError();
        }
    }
}
