package com.desafio.douglas.wmovie.util;

import android.app.Application;

import com.desafio.douglas.wmovie.dao.DatabaseManager;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by worlo on 06/04/2017.
 */

public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseManager.init(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

    }
}