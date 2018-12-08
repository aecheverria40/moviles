package com.example.abiel.examenmoviles;

public class TrabajadorModel {
    private int id;
    public String Nombre;
    public String Apellido;

    public TrabajadorModel(int t_id, String nombre, String apellido){
        id = t_id;
        Nombre = nombre;
        Apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}
