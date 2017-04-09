package com.desafio.douglas.wmovie.dao;

import android.content.Context;

import com.desafio.douglas.wmovie.model.DetailMovie;

/**
 * Created by worlo on 07/04/2017.
 */

public class DetailMovieDao extends DaoBase<DetailMovie> {

    private static DetailMovieDao dao;

    private DetailMovieDao(Context ctx) {
        super();
        super.ctx = ctx;
    }

    public static DetailMovieDao getInstance(Context ctx) {
        if (dao == null) {
            dao = new DetailMovieDao(ctx);
        }
        return dao;
    }
}
