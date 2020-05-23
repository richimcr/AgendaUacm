package com.mcr.agendauacm.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mcr.agendauacm.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParseJson {
    String json="";
    InputStream inputStream;
    BufferedReader reader;
    List<Materia> materias;
    Gson gson;
    String carrera;

    public ParseJson(Context context, String carrera)
    {
        Log.e("Erroooooooooooooooor",""+carrera);
        switch (carrera)
        {
            case "Licenciatura en Ingeniería de Software":
                inputStream = context.getResources().openRawResource(R.raw.software);
                break;
                default:
                    inputStream = context.getResources().openRawResource(R.raw.software);
                    break;
        }
        reader=new BufferedReader(new InputStreamReader(inputStream));
        materias = new ArrayList<>();
        gson = new Gson();
        this.carrera=carrera;
    }

    private String getJsonString()
    {
        String aux="";
        try
        {
            while( (aux=reader.readLine())!=null)
            {
                json+=aux;
            }
        }
        catch (Exception error)
        {
            Log.e("Error lectura json",error.getMessage());
        }
        return json;
    }

    public List<Materia> getMaterias()
    {
        Type materiasType =  new TypeToken<List<Materia>>(){}.getType();
        gson = new Gson();
        List<Materia> materias= gson.fromJson(getJsonString(), materiasType);

        return materias;
    }

}
