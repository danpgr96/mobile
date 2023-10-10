package com.application.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.application.mobile.dialogActivity.Demo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGame(View v) {
        Intent i = new Intent(this, Play.class);
        startActivity(i);
    }

    public void rules(View v) {
        Intent i = new Intent(this, Rules.class);
        startActivity(i);
    }

    public void demo(View v) {
        Intent i = new Intent(this, Demo.class);
        startActivity(i);
    }

    public void twitter(View v) {
        Uri uri = Uri.parse("https://www.twitter.com/");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    public void instagram(View v) {
        Uri uri = Uri.parse("https://www.instagram.com/");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    public void facebook(View v) {
        Uri uri = Uri.parse("https://www.facebook.com/");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
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