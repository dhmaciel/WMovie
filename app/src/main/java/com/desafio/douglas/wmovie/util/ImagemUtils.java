package com.desafio.douglas.wmovie.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.desafio.douglas.wmovie.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by worlo on 10/04/2017.
 */

public class ImagemUtils {

    private static final String TAG = ImagemUtils.class.getName();

    public static void loadImageCacheOrOnLine(final Context context, final String imgUrl, final ImageView imageView) {
        Picasso.with(context)
                .load(imgUrl)
                .placeholder(R.mipmap.ic_wallpaper)
                .error(R.mipmap.ic_wallpaper)
                .resize(150, 150)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("Load Image", "Sucesso OFFLine");
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(imgUrl)
                                .placeholder(R.mipmap.ic_wallpaper)
                                .error(R.mipmap.ic_wallpaper)
                                .resize(150, 150)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.e("Load Image", "Sucesso OnLine");
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }

    public static void loadImageCollapsingCacheOrOnLine(final Context context, final String imgUrl, final ImageView imageView) {
        Picasso.with(context)
                .load(imgUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("Load Image", "Sucesso OFFLine");
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(imgUrl)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.e("Load Image", "Sucesso OnLine");
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }
}
