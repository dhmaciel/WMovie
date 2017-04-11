package com.desafio.douglas.wmovie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by worlo on 10/04/2017.
 */

public class ConexaoUtils {

    private static final String TAG = ConexaoUtils.class.getName();

    public static boolean isInternetAtiva(Context ctx) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            boolean isAtiva = cm.getActiveNetworkInfo() != null &&
                    cm.getActiveNetworkInfo().isConnectedOrConnecting();

            if (isAtiva) {
                return true;
            }

        } catch (Exception ex) {
            Log.e(TAG, "isInternetAtivaComMsg", ex);
        }

        return false;
    }
}
