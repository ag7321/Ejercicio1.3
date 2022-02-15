package com.example.ejercicio13.tablas;

import java.io.Serializable;

public class Personas implements Serializable {
    private Integer id;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String correo;
    private String direccion;
    public Personas(){

    }
    public Personas(Integer id, String nombres, String apellidos, Integer edad, String correo,String direccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() { return direccion; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) { this.direccion = direccion; }
}
