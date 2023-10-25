package com.application.mobile.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.application.mobile.Generic;
import com.application.mobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGameRpsBasic(View v) {
        Generic.navigateToActivity(this, RpsBasic.class);
    }
    public void playGameRpsPlus(View v) {
        Generic.navigateToActivity(this, RpsPlus.class);
    }

    public void slotMachine(View v) {
        Generic.navigateToActivity(this, SlotMachine.class);
    }

    public void holdIt(View v) {
        Generic.navigateToActivity(this, HoldIt.class);
    }

    public void ticTacToe(View v) {
        Generic.navigateToActivity(this, TitTacToe.class);
    }

    public void twitter(View v) {
        Generic.navigateToWebsite(this,Generic.URL_TWITTER);
    }

    public void instagram(View v) {
        Generic.navigateToWebsite(this,Generic.URL_IG);
    }

    public void facebook(View v) {
        Generic.navigateToWebsite(this,Generic.URL_FB);
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