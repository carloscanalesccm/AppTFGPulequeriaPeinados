package com.example.apppulequeriapeinados;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;
/*
    Clase principal de la aplicación.
    Cuenta con los métodos para ir a otros intent y para reproducir la música.
 */
public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl{
    MediaPlayer sonido;
    MediaController controles;
    //Se define la URL para ver las políticcas de privacidad y protección de datos dentro de la variable URLPoliticas
    private String URLpoliticas = "https://portal.mineco.gob.es/es-es/ministerio/Paginas/Politica_de_privacidad.aspx";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ocultar ActionBar
        getSupportActionBar().hide();

        //Agregar sonido y controlador
        sonido= MediaPlayer.create(this,R.raw.jazzintro);//Indica la canción y su ubicación
        controles=new MediaController(this);
        controles.setMediaPlayer(this);//Establece el MediaPlayer en la actividad
        controles.setAnchorView(findViewById(R.id.vista_cotroles_sonido));//Lo configura por el layout
    }
    /*
        Método que te lleva a las políticas
     */
    public void irPoliticas(View view){
        Intent irP = new Intent(Intent.ACTION_VIEW);
        irP.setData(Uri.parse(URLpoliticas)); //UrlPolíticas definido con URL de la página  al principio de la clase
        startActivity(irP);
        sonido.stop(); //Para la música cuando el método es llamado
    }
    /*
        Método que te lleva a  la ventana de pedir citas
    */
    public void irCitas(View view){
        Intent irC = new Intent(this, Activity_Citas.class);
        startActivity(irC);
        sonido.stop(); //Para la música cuando el método es llamado
    }

    /*
        Método que te lleva a la información de la peluqueria
     */
    public void irInfo(View view){
        Intent irInfo = new Intent(this, Activity_Info.class);
        startActivity(irInfo);
        sonido.stop();//Para la música cuando el método es llamado
    }
    /*
        Método que te lleva a las fotos de la peluqueria
    */
    public void irFotos(View view){
        Intent irF = new Intent(this, Activity_Fotos.class); //cambiarafotos
        startActivity(irF);
        sonido.stop();//Para la música cuando el método es llamado
    }
    /*
        Método que abre Google Maps y muestra la ubicación
        La función del Google Maps se configura en la Activity_Ubicacion
    */
    public void irCUbicacion(View view){
        Intent irU = new Intent(this, Activity_Ubicacion.class);
        startActivity(irU);
        sonido.stop();//Para la música cuando el método es llamado
    }

    /*
        Métodos para configurar los ajustes del MediaPlayerController
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