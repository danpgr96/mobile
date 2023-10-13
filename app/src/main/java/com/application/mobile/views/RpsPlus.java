package com.application.mobile.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.mobile.R;
import com.application.mobile.dialogActivity.Empataste;
import com.application.mobile.dialogActivity.Empezar;
import com.application.mobile.dialogActivity.Ganaste;
import com.application.mobile.dialogActivity.Perdiste;

import java.util.Objects;
import java.util.Random;

public class RpsPlus extends AppCompatActivity {

    private TextView txtMarcadorIa,txtMarcadorJugador;
    private ImageView iaSelect,jugadorSelect;
    private ScrollView scrollView;
    Integer puntuacionJugador = 0,puntuacionMaguina = 0;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps_plus);

        txtMarcadorIa = findViewById(R.id.tvIaMarcador);
        txtMarcadorJugador = findViewById(R.id.tvJugadorMarcador);

        iaSelect = findViewById(R.id.ivIaSelect);
        jugadorSelect = findViewById(R.id.ivJugadorSelect);

        scrollView = findViewById(R.id.scrollView);
    }

    public void piedra(View v){
        contexto = this;
        jugadorSelect.setImageResource(R.drawable.piedra);
        logicPartida(turno("Piedra"), contexto);
    }

    public void papel(View v){
        contexto = this;
        jugadorSelect.setImageResource(R.drawable.papel);
        logicPartida(turno("Papel"), contexto);
    }

    public void tijera(View v){
        contexto = this;
        jugadorSelect.setImageResource(R.drawable.tijeras);
        logicPartida(turno("Tijera"), contexto);
    }

    public void lagarto(View v){
        contexto = this;
        jugadorSelect.setImageResource(R.drawable.lagarto);
        logicPartida(turno("Lagarto"), contexto);
    }

    public void spock(View v){
        contexto = this;
        jugadorSelect.setImageResource(R.drawable.spock);
        logicPartida(turno("Spock"), contexto);
    }

    private void logicPartida(String mensaje, Context context) {
        switch (mensaje) {
            case "GANASTE":
                new Ganaste(context);
                break;
            case "EMPATE":
                new Empataste(context);
                break;
            case "PERDISTE":
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
            ia_selecciono ="Piedra";
        }else if (ia_selecciono_num == 2) {
            ia_selecciono = "Papel";
        }else if (ia_selecciono_num == 3) {
            ia_selecciono = "Tijera";
        }else if (ia_selecciono_num == 4) {
            ia_selecciono = "Lagarto";
        }else {
            ia_selecciono = "Spock";
        }

        switch (ia_selecciono) {
            case "Piedra":
                iaSelect.setImageResource(R.drawable.piedra);
                break;
            case "Papel":
                iaSelect.setImageResource(R.drawable.papel);
                break;
            case "Tijera":
                iaSelect.setImageResource(R.drawable.tijeras);
                break;
            case "Lagarto":
                iaSelect.setImageResource(R.drawable.lagarto);
                break;
            case "Spock":
                iaSelect.setImageResource(R.drawable.spock);
                break;
        }

        if (ia_selecciono.equals(elige)){
            return "EMPATE";
        }else if (Objects.equals(elige, "Piedra") && (ia_selecciono.equals("Tijera") || ia_selecciono.equals("Lagarto"))){
            puntuacionJugador++;
            return "GANASTE";
        }else if (Objects.equals(elige, "Tijera") && (ia_selecciono.equals("Papel") || ia_selecciono.equals("Lagarto"))){
            puntuacionJugador++;
            return "GANASTE";
        }else if (Objects.equals(elige, "Papel") && (ia_selecciono.equals("Piedra") || ia_selecciono.equals("Spock"))){
            puntuacionJugador++;
            return "GANASTE";
        }else if (Objects.equals(elige, "Lagarto") && (ia_selecciono.equals("Spock") || ia_selecciono.equals("Papel"))){
            puntuacionJugador++;
            return "GANASTE";
        }else if (Objects.equals(elige, "Spock") && (ia_selecciono.equals("Tijera") || ia_selecciono.equals("Piedra"))){
            puntuacionJugador++;
            return "GANASTE";
        }else {
            puntuacionMaguina++;
            return "PERDISTE";
        }
    }
}