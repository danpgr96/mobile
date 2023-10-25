package com.application.mobile.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.mobile.R;
import com.application.mobile.entities.Wheel;

import java.util.Random;

@SuppressLint("SetTextI18n")
public class SlotMachine extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private Wheel wheel1,wheel2,wheel3;
    private Button btnSpin,btnAv1,btnAv2,btnAv3;
    int coins = 100;
    boolean starAv = false;
    private Integer next = 0;
    private TextView msg;
    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        btnSpin = findViewById(R.id.btnSpin);
        btnAv1 = findViewById(R.id.btnAv1);
        btnAv2 = findViewById(R.id.btnAv2);
        btnAv3 = findViewById(R.id.btnAv3);
        msg = findViewById(R.id.tvCoin);

        msg.setText("Saldo: " + coins + " €");

        disableAvButtons();

        btnSpin.setOnClickListener(view -> {
            if (coins == 0){
                finish();
            }
            coins = coins - 10;
            msg.setText("Saldo: " + coins + " €");

            disableButtons();

            wheel1 = new Wheel(img -> runOnUiThread(() -> img1.setImageResource(img)), 200, randomLong(0, 200));
            wheel1.start();

            wheel2 = new Wheel(img -> runOnUiThread(() -> img2.setImageResource(img)), 200, randomLong(150, 400));
            wheel2.start();

            wheel3 = new Wheel(img -> runOnUiThread(() -> img3.setImageResource(img)), 200, randomLong(150, 400));
            wheel3.start();

            countWheels();
        });

        btnAv1.setOnClickListener(view -> validarNextValue(wheel1));

        btnAv2.setOnClickListener(view -> validarNextValue(wheel2));

        btnAv3.setOnClickListener(view -> validarNextValue(wheel3));
    }

    public void countWheels() {
        new CountDownTimer(4000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @SuppressLint("UnsafeIntentLaunch")
            @Override
            public void onFinish() {
                wheel1.stopWheel();
                wheel2.stopWheel();
                wheel3.stopWheel();

                if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                    coins = coins + 30;
                    msg.setText("Saldo: " + coins + " €");
                    Toast.makeText(SlotMachine.this,"Ganaste 30€", Toast.LENGTH_LONG).show();
                } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex
                        || wheel1.currentIndex == wheel3.currentIndex) {
                    starAv = true;
                } else {
                    Toast.makeText(SlotMachine.this,"Perdiste!", Toast.LENGTH_LONG).show();
                }
                if (!starAv) {
                    enableSpinButton();
                    cancel();
                } else {
                    if (new Random().nextBoolean()) {
                        btnSpin.setEnabled(false);
                        btnAv1.setEnabled(true);
                        btnAv2.setEnabled(true);
                        btnAv3.setEnabled(true);
                    } else {
                        cancel();
                        enableSpinButton();
                    }
                }
            }
        }.start();
        starAv = false;
    }

    private void disableButtons() {
        btnSpin.setEnabled(false);
        btnAv1.setEnabled(false);
        btnAv2.setEnabled(false);
        btnAv3.setEnabled(false);
    }

    private void disableAvButtons() {
        btnAv1.setEnabled(false);
        btnAv2.setEnabled(false);
        btnAv3.setEnabled(false);
    }

    private void enableSpinButton() {
        btnSpin.setEnabled(true);
        btnAv1.setEnabled(false);
        btnAv2.setEnabled(false);
        btnAv3.setEnabled(false);
    }

    private void validacionWheels() {
        if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
            coins = coins + 30;
            Toast.makeText(SlotMachine.this,"Ganaste 50€", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SlotMachine.this,"Perdiste!", Toast.LENGTH_LONG).show();
        }
        enableSpinButton();
    }

    private void validarNextValue(Wheel wheel) {
        wheel.setNewImage();
        if (next == 2) {
            disableAvButtons();
            validacionWheels();
            next = 0;
        } else {
            next++;
            if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                coins = coins + 30;
                Toast.makeText(SlotMachine.this,"Ganaste 50€", Toast.LENGTH_LONG).show();
                disableAvButtons();
                enableSpinButton();
            }
        }
    }

    public void volver (View v){
        finish();
    }
}