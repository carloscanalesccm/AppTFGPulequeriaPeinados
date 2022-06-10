package com.example.apppulequeriapeinados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/*
    Clase en la que se realizan todas las operaciones hacia la base de datos
    Permite insertar datos, modificarlos, eliminarlos y comprobarlos dentro de la base de datos.
 */
public class Activity_Citas extends AppCompatActivity {

    //Declaraciones del layout
    private TextView txt_Consultar, txt_ConsultarDNI;
    private EditText edt_Nombre,edt_Apellidos,edt_DNI,edt_Telefono,edt_Dia,edt_Mes, edt_Hora, edt_Minutos;
    private Button btn_Insertar, btn_Delete, btn_Consultar, btn_Actualizar, btn_ConsultarDNI;
    //Declaración base de datos de Firebase
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        getSupportActionBar().hide(); //Oculta ActionBar

        //Referencias Layout
        txt_Consultar = (TextView) findViewById(R.id.txt_Consultar);
        txt_ConsultarDNI = (TextView) findViewById(R.id.txt_ConsultarDNI);
        edt_Nombre = (EditText)findViewById(R.id.edt_Nombre);
        edt_Apellidos = (EditText) findViewById(R.id.edt_Apellidos);
        edt_DNI = (EditText)findViewById(R.id.edt_DNI);
        edt_Telefono = (EditText) findViewById(R.id.edt_Telefono);
        edt_Dia = (EditText) findViewById(R.id.edt_Dia);
        edt_Mes = (EditText) findViewById(R.id.edt_Mes);
        edt_Hora = (EditText) findViewById(R.id.edt_Hora);
        edt_Minutos = (EditText) findViewById(R.id.edt_Minutos);
        btn_Insertar = (Button) findViewById(R.id.btn_Insertar);
        btn_Delete = (Button) findViewById(R.id.btn_Delete);
        btn_Actualizar = (Button) findViewById(R.id.btn_Actualizar);
        btn_Consultar = (Button) findViewById(R.id.btn_Consultar);
        btn_ConsultarDNI = (Button) findViewById(R.id.btn_ConsultarDNI);

        /*
            Método que se activa al pulsar el botón definido como btn_Insertar
            Permite agregar registros a la base de datos
         */
        btn_Insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Comprueba que todos los datos estén insertados
                if(edt_DNI.getText().toString().equals("")){
                    txt_Consultar.setText("Inserte un DNI");
                }else if(edt_Nombre.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte su nombre");
                }else if(edt_Apellidos.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte sus apellidos");
                }else if(edt_Telefono.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte su telefono");
                }else if(edt_Dia.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte un día");
                }else if(edt_Mes.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte un mes");
                }else if(edt_Hora.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte una hora");
                }else if(edt_Minutos.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte los minutos");
                }else{
                //Actua si hora y DNI ocupados
                 if(txt_Consultar.getText().toString().equals("Hora ocupada") && txt_ConsultarDNI.getText().toString().equals("Usted ya tiene una cita")){
                        txt_Consultar.setText("");
                        resetearFecha();
                        txt_ConsultarDNI.setText("");
                        resetearDNI();
                 //Actua si hora ocupada
                 } else if (txt_Consultar.getText().toString().equals("Hora ocupada")) {
                    txt_Consultar.setText("");
                    txt_ConsultarDNI.setText("");
                    resetearFecha();
                 //Actua si DNI ocupado
                } else if(txt_ConsultarDNI.getText().toString().equals("Usted ya tiene una cita")){
                    txt_Consultar.setText("");
                    txt_ConsultarDNI.setText("");
                    resetearDNI();
                //Si DNI y Hora libre, agrega los datos
                } else {
                    Map<String, Object> ClienteA = new HashMap<>();
                    ClienteA.put("Nombre", edt_Nombre.getText().toString());
                    ClienteA.put("Apellido", edt_Apellidos.getText().toString());
                    ClienteA.put("Telefono", edt_Telefono.getText().toString());
                    ClienteA.put("Dia", edt_Dia.getText().toString());
                    ClienteA.put("Mes", edt_Mes.getText().toString());
                    ClienteA.put("Hora", edt_Hora.getText().toString());
                    ClienteA.put("Minutos", edt_Minutos.getText().toString());

                    db.collection("Clientes").document(edt_DNI.getText().toString())
                            .set(ClienteA)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                    txt_Consultar.setText("Cita Agregada");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);

                                }
                            });
                    resetearValores();
                }
                }
            }
        });

        /*
            Método que se activa al pulsar el botón definido como btn_Delete
            Borra los datos que tengan el DNI pasado por la aplicación en el campo edt_DNI
         */
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_Consultar.setText(""); //Pone el campo txt_Consultar vacio al pulsar el botón
                //Obliga a que el usuario inserte un DNI
                if(edt_DNI.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte un DNI");
                }else {
                    //Borra el documento deseado
                    db.collection("Clientes").document(edt_DNI.getText().toString())
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    txt_Consultar.setText("Cita borrada");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    txt_Consultar.setText("No se ha podido borrar la cita");
                                }
                            });

                    resetearValores();
                }
            }
        });

        /*
            Método que se activa al pulsar el botón definido como btn_Actualizar
            Actualiza los datos de la tabla de la base de datos
            Si el DNI no ha sido usado aún, agrega la cita

         */
        btn_Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_Consultar.setText(""); //Define el campo txt_Consultar como vacio al lanzar la aplicación
                //Comprueba que todos los datos han sido rellenados
                if(edt_DNI.getText().toString().equals("")){
                    txt_Consultar.setText("Inserte un DNI");
                }else if(edt_Nombre.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte su nombre");
                }else if(edt_Apellidos.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte sus apellidos");
                }else if(edt_Telefono.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte su telefono");
                }else if(edt_Dia.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte un día");
                }else if(edt_Mes.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte un mes");
                }else if(edt_Hora.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte una hora");
                }else if(edt_Minutos.getText().toString().equals("")) {
                    txt_Consultar.setText("Inserte los minutos");
                }else{
                    //Actua si la hora y el DNI no están disponibles
                    if(txt_Consultar.getText().toString().equals("Hora ocupada") && txt_ConsultarDNI.getText().toString().equals("Usted ya tiene una cita")){
                        txt_Consultar.setText("");
                        resetearFecha();
                        txt_ConsultarDNI.setText("");
                        resetearDNI();
                    //Actua si la hora está ocupada
                    } else if (txt_Consultar.getText().toString().equals("Hora ocupada")) {
                        txt_Consultar.setText("");
                        txt_ConsultarDNI.setText("");
                        resetearFecha();
                    //Actua si el DNI está ocupado
                    } else if(txt_ConsultarDNI.getText().toString().equals("Usted ya tiene una cita")){
                        txt_Consultar.setText("");
                        txt_ConsultarDNI.setText("");
                        resetearDNI();
                    //Actualiza o inserta los datos usando el campo DNI como referencia
                    } else {
                    Map<String, Object> ClienteA = new HashMap<>();
                    ClienteA.put("Nombre", edt_Nombre.getText().toString());
                    ClienteA.put("Apellido", edt_Apellidos.getText().toString());
                    ClienteA.put("Telefono", edt_Telefono.getText().toString());
                    ClienteA.put("Dia", edt_Dia.getText().toString());
                    ClienteA.put("Mes", edt_Mes.getText().toString());
                    ClienteA.put("Hora", edt_Hora.getText().toString());
                    ClienteA.put("Minutos", edt_Minutos.getText().toString());

                    db.collection("Clientes").document(edt_DNI.getText().toString())
                            .set(ClienteA)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    txt_Consultar.setText("Usuario Actualizado");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    txt_Consultar.setText("No se ha podido actualizar");
                                }
                            });

                    resetearValores();
                    }
                }
            }
        });

        /*
            Método que se activa al pulsar el botón definido como btn_Consultar
            Busca en la base de datos el dia, el mes, la hora y los minutos introducidos, y si los encuentra,
            indica al usuario que la fecha y la hora no están disponibles
         */
        btn_Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Clientes")
                        .whereEqualTo("Dia", edt_Dia.getText().toString()) //Comprueba Dia
                        .whereEqualTo("Mes", edt_Mes.getText().toString())//Comprueba Mes
                        .whereEqualTo("Hora", edt_Hora.getText().toString())//Comprueba Hora
                        .whereEqualTo("Minutos", edt_Minutos.getText().toString())//Comprueba minutos
                        .get() //Coge los datos para la consulta

                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        txt_Consultar.setText("Hora ocupada");
                                        //El campo txt_Consultar pasa a indicar "Hora Ocupada" para notificar al cliente
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                    //no he conseguido que llegue aquí
                                    txt_Consultar.setText("Hora disponible");
                                }
                            }
                        });
            }
        });

        /*
            Método que se activa al pulsar el botón definido como btn_ConsultarDNI
            Busca en la base de datos el DNI introducido, y si los encuentra,
            indica al usuario que el DNI ya ha sido usado
         */
        btn_ConsultarDNI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Clientes")
                        .whereEqualTo(FieldPath.documentId().toString(),edt_DNI.getText().toString())//Comprueba DNI
                        .get() //Coge los datos para la consulta
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        txt_ConsultarDNI.setText("Usted ya tiene una cita");
                                        //El campo txt_ConsultarDNI pasa a indicar "Usted ya tiene una cita" para notificar al cliente
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                    //no he conseguido que llegue aquí
                                    txt_ConsultarDNI.setText("Usted no tiene ninguna cita");
                                }
                            }
                        });



                //resetearValores();

            }
        });
    }

    /*
        Método que al llamarlo, resetea los valores de todos los campos del layout
     */
    private void resetearValores() {
        edt_DNI.setText("");
        edt_Nombre.setText("");
        edt_Apellidos.setText("");
        edt_Telefono.setText("");
        edt_Dia.setText("");
        edt_Mes.setText("");
        edt_Hora.setText("");
        edt_Minutos.setText("");

    }
    /*
        Método que al llamarlo, resetea los valores de los campos Dia, Mes, Hora y minutos del layout
    */
    private void resetearFecha() {
        edt_Dia.setText("");
        edt_Mes.setText("");
        edt_Hora.setText("");
        edt_Minutos.setText("");

    }
    /*
        Método que al llamarlo, resetea los valores del campo DNI del layout
    */
    private void resetearDNI() {
        edt_DNI.setText("");

    }
    /*
        Método que al llamarlo, te lleva de vuelta a la ventana de inicio
    */
    public void irInicio(View view){
        Intent irIni = new Intent(this, MainActivity.class);
        startActivity(irIni);
    }

}