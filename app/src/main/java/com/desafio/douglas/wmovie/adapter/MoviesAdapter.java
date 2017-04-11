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
import com.desafio.douglas.wmovie.activity.MainActivity;
import com.desafio.douglas.wmovie.activity.MovieDetailActivity;
import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.util.ImagemUtils;

import java.util.List;

/**
 * Created by worlo on 04/04/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private final String TAG = MoviesAdapter.class.getName();
    private List<DetailMovie> searchList;
    private int rowLayout;
    private Context context;

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePoster;
        TextView txtTitle;
        TextView txtYear;
        TextView txtImdbRate;
        TextView txtMetacriticRate;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            imagePoster = (ImageView) itemView.findViewById(R.id.img_movies_poster);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_movies_title);
            txtYear = (TextView) itemView.findViewById(R.id.txt_movies_year);
            txtImdbRate = (TextView) itemView.findViewById(R.id.txt_movies_imdb_rate);
            txtMetacriticRate = (TextView) itemView.findViewById(R.id.txt_movies_metacritic_rate);
        }
    }

    public MoviesAdapter(List<DetailMovie> items, int rowLayout, Context context) {
        this.searchList = items;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        final DetailMovie detailMovie = searchList.get(position);

        holder.txtTitle.setText(detailMovie.getTitle());
        holder.txtYear.setText(detailMovie.getYear());
        holder.txtImdbRate.setText(detailMovie.getImdbRating());
        holder.txtMetacriticRate.setText(detailMovie.getMetascore());

        ImagemUtils.loadImageCacheOrOnLine(context, detailMovie.getPoster(), holder.imagePoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("DetailMovie", detailMovie);
                intent.putExtra(MovieDetailActivity.DELETE_MODE, MovieDetailActivity.DELETE_MODE);
                ((Activity) context).startActivityForResult(intent, MainActivity.MOVIES_LOAD);
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
