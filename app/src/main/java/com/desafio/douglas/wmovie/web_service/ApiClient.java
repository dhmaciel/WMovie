package com.desafio.douglas.wmovie.web_service;

import android.content.Context;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.util.Utils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by worlo on 05/04/2017.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    /**
     * Singleton que obtem a instância do Retrofit.
     *
     * @param ctx
     * @return
     */
    public static Retrofit getClient(Context ctx) {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(ctx.getString(R.string.config_base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createCacheClient(ctx))
                    .build();
        }
        return retrofit;
    }

    private static Interceptor getCacheReceptor(final Context context){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (Utils.isInternetAtiva(context)) {
                    int maxAge = 86400; // read from cache for 24 hour.
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
    }

    private static OkHttpClient createCacheClient(Context context){

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 20 * 1024 * 1024; // 20 MiB

        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(getCacheReceptor(context))
                .addInterceptor(getCacheReceptor(context))
                .cache(cache)
                .build();
    }
}
