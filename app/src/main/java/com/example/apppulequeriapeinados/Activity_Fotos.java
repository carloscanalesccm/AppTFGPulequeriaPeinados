package com.example.apppulequeriapeinados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;

/*
    Clase que muestra fotos del local de la peluquería
    Implementa un MediaController.MediaPlayerControl para realizar operaciones con la música
 */
public class Activity_Fotos extends AppCompatActivity implements MediaController.MediaPlayerControl {
    MediaPlayer sonido;
    MediaController controles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        getSupportActionBar().hide(); //Oculta ActionBar

        //Configuración de la animación que realiza el carrusel de imágenes
        ImageView imagen = (ImageView) findViewById(R.id.img_Pelu);
        AnimationDrawable animacion = (AnimationDrawable) imagen.getDrawable();
        animacion.start();

        //Configuración del reproductor de la música
        sonido= MediaPlayer.create(this,R.raw.jazzfotos); //Indica la canción y su ubicación
        controles=new MediaController(this);
        controles.setMediaPlayer(this); //Establece el MediaPlayer en la actividad
        controles.setAnchorView(findViewById(R.id.vista_cotroles_sonido)); //Lo configura por el layout
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
        Método que al llamarlo te lleva a la ventana de información sobre la peluquería
    */
    public void irInfo(View view){
        Intent irI = new Intent(this, Activity_Info.class);
        startActivity(irI);
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