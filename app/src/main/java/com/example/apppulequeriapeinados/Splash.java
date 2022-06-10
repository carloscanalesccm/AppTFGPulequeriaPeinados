package com.example.apppulequeriapeinados;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

/*
    Clase donde se configura el SPlash
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); //Oculta ActionBar
        setContentView(R.layout.activity_splash); //establece que se vea el layout de Activity_Splash
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Timer().schedule(new TimerTask() {

            /*
                Método que se activa al lanzar la aplicación
             */
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Splash.this.finish();
            }
        }, 2000); //Indica el tiempo de duración del SPlash
    }
}