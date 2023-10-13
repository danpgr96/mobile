package com.application.mobile.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.application.mobile.Generic;
import com.application.mobile.R;
import com.application.mobile.dialogActivity.Demo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGame(View v) {
        Generic.navigateToActivity(this, Play.class);
    }

    public void rules(View v) {
        Generic.navigateToActivity(this, Rules.class);
    }

    public void demo(View v) {
        Generic.navigateToActivity(this, Demo.class);
    }

    public void twitter(View v) {
        Generic.navigateToWebsite(this,"https://www.twitter.com/");
    }

    public void instagram(View v) {
        Generic.navigateToWebsite(this,"https://www.instagram.com/");
    }

    public void facebook(View v) {
        Generic.navigateToWebsite(this,"https://www.facebook.com/");
    }

    public void salir(View v) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.alertsalir)
                .setTitle("¿Seguro qué quieres salir?")
                .setCancelable(false)
                .setNegativeButton("No", null)
                .setPositiveButton("Si", (dialog, which) -> moveTaskToBack(true)).show();
    }
}