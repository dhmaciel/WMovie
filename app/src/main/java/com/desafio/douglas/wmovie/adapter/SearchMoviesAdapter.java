package com.desafio.douglas.wmovie.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.activity.MovieDetailActivity;
import com.desafio.douglas.wmovie.activity.SearchResultsActivity;
import com.desafio.douglas.wmovie.model.Search;
import com.desafio.douglas.wmovie.util.ImagemUtils;

import java.util.List;

/**
 * Created by worlo on 05/04/2017.
 */

public class SearchMoviesAdapter  extends RecyclerView.Adapter<SearchMoviesAdapter.SearchMoviesViewHolder>{

    private final String TAG = MoviesAdapter.class.getName();
    private List<Search> searchList;
    private int rowLayout;
    private Context context;

    public class SearchMoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView txtTitle;
        TextView txtYear;

        public SearchMoviesViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.img_search_poster);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_search_title);
            txtYear = (TextView) itemView.findViewById(R.id.txt_search_year);
        }
    }

    public SearchMoviesAdapter(List<Search> searchList, int rowLayout, Context context) {
        this.searchList = searchList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public SearchMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SearchMoviesAdapter.SearchMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchMoviesViewHolder holder, int position) {
        final Search search = searchList.get(position);

        ImagemUtils.loadImageCacheOrOnLine(context, search.getPoster(), holder.imageViewPoster);

        holder.txtTitle.setText(search.getTitle());
        holder.txtYear.setText(search.getYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("search", search);
                intent.putExtra(MovieDetailActivity.INSERT_MODE, MovieDetailActivity.INSERT_MODE);
                ((Activity) context).startActivityForResult(intent, SearchResultsActivity.MOVIES_UPDATE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
