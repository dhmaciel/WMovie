package com.desafio.douglas.wmovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.background_task.DownloadDetailMovieAsync;
import com.desafio.douglas.wmovie.background_task.ICallBack;
import com.desafio.douglas.wmovie.background_task.IDownloadDetailMovieCallBack;
import com.desafio.douglas.wmovie.background_task.SaveOrDeleteMovieDetailAsync;
import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.model.Search;
import com.desafio.douglas.wmovie.util.ImagemUtils;
import com.desafio.douglas.wmovie.widget.SquareImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity implements IDownloadDetailMovieCallBack, ICallBack{

    @BindView(R.id.detail_coordinator)
    public CoordinatorLayout coordinatorLayoutDetail;

    @BindView(R.id.detail_app_bar)
    public AppBarLayout appBarLayoutDetail;

    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.fab_detail)
    public FloatingActionButton fabAction;

    @BindView(R.id.txt_detail_metacritic_rate)
    public TextView txtDetailMetaRate;

    @BindView(R.id.txt_detail_imdb_rate)
    public TextView txtDetailImdbRate;

    @BindView(R.id.txt_detail_year)
    public TextView txtDetailYear;

    @BindView(R.id.txt_detail_plot)
    public TextView txtDetailPlot;

    @BindView(R.id.txt_detail_actors)
    public TextView txtDetailActors;

    @BindView(R.id.txt_detail_title)
    public TextView txtDetailTitle;

    @BindView(R.id.image_detail)
    public SquareImageView imgDetail;

    private final String TAG = MovieDetailActivity.class.getName();
    private DetailMovie detailMovie;
    private Mode currMode;
    public static final String INSERT_MODE = "INSERT_MODE";
    public static final String DELETE_MODE = "UPDATE_MODE";

    private static final String EXTRA_IMAGE = "com.desafio.douglas.wmovie.extraImage";
    private static final String EXTRA_TITLE = "com.desafio.douglas.wmovie.extraTitle";

    public enum Mode {
        Insert,
        Delete
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        ViewCompat.setTransitionName(appBarLayoutDetail, EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.containsKey(INSERT_MODE)){
                currMode = Mode.Insert;
                Search search = (Search) bundle.get("search");
                new DownloadDetailMovieAsync(this, this).execute(search.getImdbID());
            }else {
                currMode = Mode.Delete;
                detailMovie = (DetailMovie) bundle.get("DetailMovie");
                carregarCampos();
            }
        }
    }

    private void carregarCampos(){
        if(detailMovie != null){
            collapsingToolbarLayout.setTitle(detailMovie.getTitle());
            txtDetailImdbRate.setText(detailMovie.getImdbRating());
            txtDetailMetaRate.setText(detailMovie.getMetascore());
            txtDetailYear.setText(detailMovie.getYear());
            txtDetailActors.setText(detailMovie.getActors());
            txtDetailPlot.setText(detailMovie.getPlot());
            txtDetailTitle.setText(detailMovie.getTitle());
            if(detailMovie.getPoster() == null || detailMovie.getPoster().equalsIgnoreCase("N/A")){
                Toast.makeText(this, getString(R.string.toast_sem_imagem_detail), Toast.LENGTH_LONG).show();
                appBarLayoutDetail.setExpanded(false);
            }else {
                ImagemUtils.loadImageCollapsingCacheOrOnLine(this, detailMovie.getPoster(), imgDetail);
            }
        }
        definirIconeAcao();
    }

    private void definirIconeAcao(){
        if(currMode == Mode.Insert){
            fabAction.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_action_save));
        }else{
            fabAction.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_action_delete));
        }
    }

    @OnClick(R.id.fab_detail)
    public void onClickFabDetail(){
        if(detailMovie != null){
            new SaveOrDeleteMovieDetailAsync(this, this, currMode).execute(detailMovie);
        }else {
            Toast.makeText(this, getString(R.string.toast_erro_save_delete_detail), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccess(DetailMovie detailMovie) {
        if(detailMovie != null){
            this.detailMovie = detailMovie;
            carregarCampos();
        }
    }

    @Override
    public void onError(String msgError) {
        Toast.makeText(this, getString(R.string.toast_erro_load_detail), Toast.LENGTH_LONG).show();
        Log.e(TAG, msgError);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, getString(R.string.toast_sucesso_save_delete_detail), Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        if(currMode == Mode.Delete){
            finish();
        }else{
            currMode = Mode.Delete;
            definirIconeAcao();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, getString(R.string.toast_erro_save_delete_detail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
