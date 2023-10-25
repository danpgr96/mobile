package com.application.mobile.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.mobile.Generic;
import com.application.mobile.R;
import com.application.mobile.dialogActivity.Empataste;
import com.application.mobile.dialogActivity.Empezar;
import com.application.mobile.dialogActivity.Ganaste;
import com.application.mobile.dialogActivity.Perdiste;

import java.util.Objects;
import java.util.Random;

public class RpsBasic extends AppCompatActivity {

    private TextView txtMarcadorIa,txtMarcadorJugador;
    private ImageView iaSelect,jugadorSelect;
    private ScrollView scrollView;
    Integer puntuacionJugador = 0,puntuacionMaguina = 0;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps_basic);

        txtMarcadorIa = findViewById(R.id.tvIaMarcadorBasic);
        txtMarcadorJugador = findViewById(R.id.tvJugadorMarcadorBasic);

        iaSelect = findViewById(R.id.ivIaSelectBasic);
        jugadorSelect = findViewById(R.id.ivJugadorSelectBasic);

        scrollView = findViewById(R.id.scrollViewBasic);
    }

    public void seleccionJugador(View v) {
        contexto = this;
        String opcion = (String) v.getTag();
        int resourceId = getResourceId(opcion);

        if (resourceId != 0) {
            jugadorSelect.setImageResource(resourceId);
            logicPartida(turno(opcion), contexto);
        }
    }

    private int getResourceId(String opcion) {
        int resourceId = 0;
        switch (opcion) {
            case Generic.PIEDRA_LOWERCASE:
                resourceId = R.drawable.piedra;
                break;
            case Generic.PAPEL_LOWERCASE:
                resourceId = R.drawable.papel;
                break;
            case Generic.TIJERA_LOWERCASE:
                resourceId = R.drawable.tijeras;
                break;
        }
        return resourceId;
    }

    private void logicPartida(String mensaje, Context context) {
        switch (mensaje) {
            case Generic.GANASTE_UPPERCASE:
                new Ganaste(context);
                break;
            case Generic.EMPATE_UPPERCASE:
                new Empataste(context);
                break;
            case Generic.PERDISTE_UPPERCASE:
                new Perdiste(context);
                break;
        }

        txtMarcadorJugador.setText(String.valueOf(puntuacionJugador));
        txtMarcadorIa.setText(String.valueOf(puntuacionMaguina));
        scrollView.post(() -> scrollView.scrollTo(0,0));
    }

    public void volver (View v){
        finish();
    }

    public void reiniciar (View v){
        contexto = this;

        puntuacionJugador = 0;
        puntuacionMaguina = 0;

        new Empezar(contexto);
        txtMarcadorJugador.setText(String.valueOf(0));
        txtMarcadorIa.setText(String.valueOf(0));
        iaSelect.setImageResource(R.drawable.interrogacion);
        jugadorSelect.setImageResource(R.drawable.interrogacion);
        scrollView.post(() -> scrollView.scrollTo(0,0));
    }

    public String turno(String elige){
        Random r = new Random();
        String ia_selecciono;
        int ia_selecciono_num = r.nextInt(5)+1;

        if (ia_selecciono_num == 1){
            ia_selecciono = Generic.PIEDRA_LOWERCASE;
        } else if (ia_selecciono_num == 2) {
            ia_selecciono = Generic.PAPEL_LOWERCASE;
        } else {
            ia_selecciono = Generic.TIJERA_LOWERCASE;
        }

        switch (ia_selecciono) {
            case Generic.PIEDRA_LOWERCASE:
                iaSelect.setImageResource(R.drawable.piedra);
                break;
            case Generic.PAPEL_LOWERCASE:
                iaSelect.setImageResource(R.drawable.papel);
                break;
            case Generic.TIJERA_LOWERCASE:
                iaSelect.setImageResource(R.drawable.tijeras);
                break;
        }

        if (ia_selecciono.equals(elige)){
            return Generic.EMPATE_UPPERCASE;
        } else if (Objects.equals(elige, Generic.PIEDRA_LOWERCASE) && (ia_selecciono.equals(Generic.TIJERA_LOWERCASE))){
            puntuacionJugador++;
            return Generic.GANASTE_UPPERCASE;
        } else if (Objects.equals(elige, Generic.TIJERA_LOWERCASE) && (ia_selecciono.equals(Generic.PAPEL_LOWERCASE))){
            puntuacionJugador++;
            return Generic.GANASTE_UPPERCASE;
        } else if (Objects.equals(elige, Generic.PAPEL_LOWERCASE) && (ia_selecciono.equals(Generic.PIEDRA_LOWERCASE))){
            puntuacionJugador++;
            return Generic.GANASTE_UPPERCASE;
        } else {
            puntuacionMaguina++;
            return Generic.PERDISTE_UPPERCASE;
        }
    }
}