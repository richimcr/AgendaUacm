package com.mcr.agendauacm.model;

import com.google.gson.annotations.SerializedName;

public class Materia {

    @SerializedName("nombre")
    String nombre;
    @SerializedName("semestre")
    String semestre;
    @SerializedName("ciclo")
    String ciclo;
    @SerializedName("estado")
    String estado;

    public Materia()
    {

    }

    public Materia(String nombre, String semestre, String ciclo,String estado) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.ciclo = ciclo;
        this.estado=estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}