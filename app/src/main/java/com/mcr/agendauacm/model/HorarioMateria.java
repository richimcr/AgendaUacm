package com.mcr.agendauacm.model;

import com.google.gson.annotations.SerializedName;

public class HorarioMateria {

    @SerializedName("id")
    String id;
    @SerializedName("nombre")
    String nombre;

    public HorarioMateria(String id,String nombre)
    {
        this.id=id;
        this.nombre=nombre;
    }

    public HorarioMateria()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
