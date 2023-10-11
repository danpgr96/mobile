package com.application.mobile.dialogActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.mobile.R;

import java.util.Objects;

public class Demo extends AppCompatActivity {

    private TextView puntuacion1,puntuacion2,contador;
    private Button btnEmpezarJ5;
    private ImageButton iBJugador1,iBJugador2,volver;
    private Integer contJ1 = 0, contJ2 = 0;
    private LinearLayout linea;
    private boolean isPulsado1 = false, isPulsado2 = false;
    private final Handler handlerJ1 = new Handler();
    private final Handler handlerJ2 = new Handler();
    Context contexto;

    private final Runnable contadorRunnableJ1 = new Runnable() {
        @Override
        public void run() {
            if (isPulsado1) {
                contJ1++;
                puntuacion1.setText(String.valueOf(contJ1));
                handlerJ1.postDelayed(this, 100);
            }
        }
    };

    private final Runnable contadorRunnableJ2 = new Runnable() {
        @Override
        public void run() {
            if (isPulsado2) {
                contJ2++;
                puntuacion2.setText(String.valueOf(contJ2));
                handlerJ2.postDelayed(this, 100);
            }
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        puntuacion1 = findViewById(R.id.tvPuntuacionJ1);
        puntuacion2 = findViewById(R.id.tvPuntuacionJ2);

        iBJugador1 = findViewById(R.id.ibPlayer1);
        iBJugador2 = findViewById(R.id.ibPlayer2);
        volver = findViewById(R.id.ibVolverDemo);
        btnEmpezarJ5 = findViewById(R.id.btnEmpezarJ5);
        contador = findViewById(R.id.tvContadorJ5);
        linea = findViewById(R.id.lineaDemoGame);

        iBJugador1.setEnabled(false);
        iBJugador2.setEnabled(false);

        iBJugador1.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                isPulsado1 = true;
                iniciarContadorJugador1();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                isPulsado1 = false;
            }
            return true;
        });

        iBJugador2.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                isPulsado2 = true;
                iniciarContadorJugador2();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                isPulsado2 = false;
            }
            return true;
        });
    }

    public void startGame(View v){
        contadorTime();
        iBJugador1.setEnabled(true);
        iBJugador2.setEnabled(true);
        btnEmpezarJ5.setVisibility(View.INVISIBLE);
        btnEmpezarJ5.setEnabled(false);
        volver.setVisibility(View.INVISIBLE);
        linea.setVisibility(View.INVISIBLE);
        volver.setEnabled(false);
    }

    public void iniciarContadorJugador1(){
       handlerJ1.postDelayed(contadorRunnableJ1, 1);
    }

    public void iniciarContadorJugador2(){
        handlerJ2.postDelayed(contadorRunnableJ2, 1);
    }

    public void contadorTime() {
        contexto = this;
        new CountDownTimer(10000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador.setText(String.valueOf(millisUntilFinished / 1000 + 1));
                puntuacion1.setText(String.valueOf(contJ1));
                puntuacion2.setText(String.valueOf(contJ2));
            }

            @SuppressLint("UnsafeIntentLaunch")
            @Override
            public void onFinish() {
                iBJugador1.setEnabled(false);
                iBJugador2.setEnabled(false);
                isPulsado1 = false;
                isPulsado2 = false;

                if (Objects.equals(contJ1, contJ2)) {
                    new JugadoresEmpate(contexto);
                    handlerEndGame();
                } else if (contJ1 > contJ2 && contJ1 <= 100) {
                    new JugadorUnoGana(contexto);
                    handlerEndGame();
                } else if (contJ2 > contJ1 && contJ2 <= 100) {
                    new JugadorDosGana(contexto);
                    handlerEndGame();
                }
            }
        }.start();
    }

    private void handlerEndGame() {
        new Handler().postDelayed(() -> {
            finish();
            startActivity(getIntent());
        }, 3000);
    }

    public void volver(View v){
        finish();
    }
}