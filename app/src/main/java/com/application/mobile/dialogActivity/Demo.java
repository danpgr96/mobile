package com.application.mobile.dialogActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.application.mobile.R;

public class Demo extends AppCompatActivity {

    private TextView puntuacion1,puntuacion2,contador;
    private Button btnEmpezarJ5;
    private ImageButton iBJugador1,iBJugador2,volver;
    private Integer contJ1 = 0, contJ2 = 0;
    private boolean isPulsado1 = false, isPulsado2 = false;
    private final Handler handlerJ1 = new Handler();
    private final Handler handlerJ2 = new Handler();

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
        volver.setEnabled(false);
    }

    public void iniciarContadorJugador1(){
       handlerJ1.postDelayed(contadorRunnableJ1, 100);
    }

    public void iniciarContadorJugador2(){
        handlerJ2.postDelayed(contadorRunnableJ2, 100);
    }

    public void contadorTime() {

        new CountDownTimer(20000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador.setText(String.valueOf(millisUntilFinished / 1000 + 1));
                puntuacion1.setText(String.valueOf(contJ1));
                puntuacion2.setText(String.valueOf(contJ2));
            }

            @Override
            public void onFinish() {
                iBJugador1.setEnabled(false);
                iBJugador2.setEnabled(false);

                if (contJ1 > contJ2) {

                    Toast.makeText(Demo.this, "Gana el Jugador 1", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(getIntent());
                        }
                    }, 3000);


                } else if (contJ1 < contJ2) {

                    Toast.makeText(Demo.this, "Gana el Jugador 2", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(getIntent());
                        }
                    }, 3000);

                } else {

                    Toast.makeText(Demo.this, "Empate", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(getIntent());
                        }
                    }, 3000);

                }


            }
        }.start();
    }

    public void volver(View v){
        finish();
    }
}