package com.example.apppulequeriapeinados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;

/*
    Clase que muestra información sobre la peluqueria
    Implementa un MediaController.MediaPlayerControl para realizar operaciones con la música
 */
public class Activity_Info extends AppCompatActivity implements MediaController.MediaPlayerControl {

    MediaPlayer sonido;
    MediaController controles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide(); //Oculta ActionBar

        //Configuración del reproductor de la música
        sonido= MediaPlayer.create(this,R.raw.jazzinfo);//Indica la canción y su ubicación
        controles=new MediaController(this);
        controles.setMediaPlayer(this);//Establece el MediaPlayer en la actividad
        controles.setAnchorView(findViewById(R.id.vista_cotroles_sonido));//Lo configura por el layout
    }
    /*
        Método que al llamarlo te lleva a la ventana de inicio
     */
    public void irInicio(View view){
        Intent irIni = new Intent(this, MainActivity.class);
        startActivity(irIni);
            sonido.stop(); //Para la música cuando el método es llamado

    }
    /*
        Método que al llamarlo te lleva a la ventana con las fotos del local de la peluqueria
    */
    public void irFotos(View view){
        Intent irF = new Intent(this, Activity_Fotos.class);
        startActivity(irF);
        sonido.stop(); //Para la música cuando el método es llamado
    }
    /*
        Métodos para controlar el reproductor de música
    */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        controles.show();
        return false;
    }

    @Override
    public void start() {
        sonido.start();
    }

    @Override
    public void pause() {
        sonido.pause();
    }

    @Override
    public int getDuration() {
        return sonido.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return sonido.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        sonido.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return sonido.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}