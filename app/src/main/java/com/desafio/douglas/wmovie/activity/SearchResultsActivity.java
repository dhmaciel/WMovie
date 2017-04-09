package com.desafio.douglas.wmovie.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.adapter.SearchMoviesAdapter;
import com.desafio.douglas.wmovie.background_task.DownloadMoviesAsync;
import com.desafio.douglas.wmovie.background_task.IDownloadMoviesCallBack;
import com.desafio.douglas.wmovie.model.Search;
import com.desafio.douglas.wmovie.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity implements IDownloadMoviesCallBack {

    @BindView(R.id.recycler_view_search)
    public RecyclerView recyclerView;

    @BindView(R.id.progress_load_search)
    public ProgressBar progressLoadRepo;

    private final String TAG = SearchResultsActivity.class.getName();
    private Context context;
    private EndlessRecyclerViewScrollListener scrollListener;
    private List<Search> searchList;
    private SearchMoviesAdapter searchMoviesAdapter;
    private LinearLayoutManager llm;
    private final int initialPage = 1;

    public static final int MOVIES_UPDATE = 101;
    public static boolean isMovieUpdateLocal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.context = this;
        handleIntent(getIntent());

        searchList = new ArrayList<>();
        searchMoviesAdapter = new SearchMoviesAdapter(searchList, R.layout.list_item_search_movies, this);
        recyclerView.setAdapter(searchMoviesAdapter);
        recyclerView.setHasFixedSize(true);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {

        llm = new LinearLayoutManager(this);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            setTitle(query);

            final IDownloadMoviesCallBack moviesCallBack = this;

            scrollListener = new EndlessRecyclerViewScrollListener(llm) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to the bottom of the list
                    //loadNextDataFromApi(page);

                    new DownloadMoviesAsync(moviesCallBack, context, page).execute(query);
                }
            };

            // Adds the scroll listener to RecyclerView
            recyclerView.addOnScrollListener(scrollListener);

            new DownloadMoviesAsync(moviesCallBack, context, initialPage).execute(query);
        }
    }

    private void popularListagemSearch(int count){
        searchMoviesAdapter.notifyItemRangeInserted(count, searchList.size() - 1);
    }

    @Override
    public void onSuccess(List<Search> list) {
        if(list != null){
            searchList.addAll(list);
        }
        int curSize = searchMoviesAdapter.getItemCount();
        popularListagemSearch(curSize);
    }

    @Override
    public void onError(String msgError) {
        Toast.makeText(this, getString(R.string.toast_erro_load_search), Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeVisibilityProgress(int visibility) {
        progressLoadRepo.setVisibility(visibility);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SearchResultsActivity.MOVIES_UPDATE){
            if(resultCode == Activity.RESULT_OK){
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                isMovieUpdateLocal = true;
                finish();
            }
        }
    }
}
