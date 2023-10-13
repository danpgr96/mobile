package com.application.mobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Generic {

    public static void navigateToActivity(Context context, Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static void navigateToWebsite(Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
