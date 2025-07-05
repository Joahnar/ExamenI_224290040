package com.pmexa.pm2e10040;

public class Contact{
    String nombre, telefono, notas;

    public Contact(String nombre, String telefono, String notas){
        this.nombre = nombre;
        this.telefono = telefono;
        this.notas = notas;
    }
    public String getNombre(){return nombre;}
    public String getTelefono(){return telefono;}
    public String getNotas() {return notas;}
}