package com.application.mobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Generic {

    public static String URL_TWITTER = "https://www.twitter.com/";
    public static String URL_FB = "https://www.facebook.com/";
    public static String URL_IG = "https://www.instagram.com/";
    public final static String PIEDRA_LOWERCASE = "Piedra";
    public final static String PAPEL_LOWERCASE = "Papel";
    public final static String TIJERA_LOWERCASE = "Tijera";
    public final static String LAGARTO_LOWERCASE = "Lagarto";
    public final static String SPOK_LOWERCASE = "Spok";
    public final static String GANASTE_UPPERCASE = "GANASTE";
    public final static String EMPATE_UPPERCASE = "EMPATASTE";
    public final static String PERDISTE_UPPERCASE = "PERDISTE";

    public static void navigateToActivity(Context context, Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static void navigateToWebsite(Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
