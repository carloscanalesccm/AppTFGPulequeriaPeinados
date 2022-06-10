package com.example.apppulequeriapeinados;

import android.text.Editable;

import com.google.firebase.Timestamp;

/*
    Clase donde se definen los datos de los clientes, su constructor, sus getter y sus setter
 */
public class Clientes {

    private String DNI;
    private String Nombre;
    private String Apellidos;
    private String Telefono;
    private String Dia;
    private String Mes;
    private String Hora;
    private String Minutos;

    //Constructor vacio
    public Clientes() {
    }
    //Constructor
    public Clientes(String nombre, String apellidos, String telefono, String dia, String mes, String hora, String minutos) {
        Nombre = nombre;
        Apellidos = apellidos;
        Telefono = telefono;
        Dia = dia;
        Mes = mes;
        Hora = hora;
        Minutos = minutos;
    }
    //Getters y Setters
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getMinutos() {
        return Minutos;
    }

    public void setMinutos(String minutos) {
        Minutos = minutos;
    }
}
