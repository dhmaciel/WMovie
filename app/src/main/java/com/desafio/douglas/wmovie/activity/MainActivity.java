package com.desafio.douglas.wmovie.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.adapter.MoviesAdapter;
import com.desafio.douglas.wmovie.background_task.ISearchMovieswLocalAsync;
import com.desafio.douglas.wmovie.background_task.SearchMoviesLocalAsync;
import com.desafio.douglas.wmovie.model.DetailMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ISearchMovieswLocalAsync{

    @BindView(R.id.recycler_view_movies)
    public RecyclerView recyclerView;

    public static final int MOVIES_LOAD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        pesquisarFilmesFavoritos();
    }

    private void pesquisarFilmesFavoritos(){
        new SearchMoviesLocalAsync(this, this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public void onSuccess(List<DetailMovie> detailMovieList) {
        if(detailMovieList.size() == 0){
            Toast.makeText(this, getString(R.string.toast_main_cadastro_vazia), Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(new MoviesAdapter(detailMovieList, R.layout.list_item_movies, this));
    }

    @Override
    public void onError(String msgError) {
        Toast.makeText(this, msgError, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            pesquisarFilmesFavoritos();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(SearchResultsActivity.isMovieUpdateLocal){
            pesquisarFilmesFavoritos();
            SearchResultsActivity.isMovieUpdateLocal = false;
        }
    }
}
