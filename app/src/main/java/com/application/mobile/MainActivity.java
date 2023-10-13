package com.application.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.application.mobile.dialogActivity.Demo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // quitarMenuSuperiorEinferior();
        setContentView(R.layout.activity_main);
    }

    public void navigateToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void navigateToWebsite(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void playGame(View v) {
        navigateToActivity(Play.class);
    }

    public void rules(View v) {
        navigateToActivity(Rules.class);
    }

    public void demo(View v) {
        navigateToActivity(Demo.class);
    }

    public void twitter(View v) {
        navigateToWebsite("https://www.twitter.com/");
    }

    public void instagram(View v) {
        navigateToWebsite("https://www.instagram.com/");
    }

    public void facebook(View v) {
        navigateToWebsite("https://www.facebook.com/");
    }

    public void salir(View v) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.alertsalir)
                .setTitle("¿Seguro qué quieres salir?")
                .setCancelable(false)
                .setNegativeButton("No", null)
                .setPositiveButton("Si", (dialog, which) -> moveTaskToBack(true)).show();
    }

    private void quitarMenuSuperiorEinferior() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}