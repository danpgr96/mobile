package com.application.mobile.dialogActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.application.mobile.R;

import java.util.Objects;

public class JugadorUnoGana extends AppCompatActivity {
    public JugadorUnoGana(Context contexto){
        final Dialog d = new Dialog(contexto);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(d.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.setContentView(R.layout.activity_jugador_uno_gana);
        d.setCancelable(true);
        d.show();
    }
}