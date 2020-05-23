package com.mcr.agendauacm.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MateriaDBHelper extends SQLiteOpenHelper {

    public MateriaDBHelper(Context context) {
        super(context, "uacmmateria", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablaExec = "CREATE TABLE MATERIA ("+
                "NOMBRE TEXT NOT NULL," +
                "SEMESTRE TEXT NOT NULL," +
                "CICLO TEXT NOT NULL," +
                "ESTADO TEXT NOT NULL)";
        db.execSQL(tablaExec);
    }

    public void insertaMaterias(List<Materia> materias)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        for(int i=0;i<materias.size();i++)
        {
            ContentValues values = new ContentValues();
            values.put("NOMBRE",materias.get(i).getNombre());
            values.put("SEMESTRE", materias.get(i).getSemestre());
            values.put("CICLO", materias.get(i).getCiclo());
            values.put("ESTADO", "N");
            sqLiteDatabase.insert("MATERIA",null,values);
            Log.d("Materia insertada",materias.get(i).getNombre());
        }
        sqLiteDatabase.close();
    }

    public List<Materia> getMaterias()
    {
        List<Materia> materias = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String columns[] = new String[]{"NOMBRE","SEMESTRE","CICLO","ESTADO"};
        Cursor c = db.query(
                "MATERIA",  // Nombre de la tabla
                columns,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                null  // Cláusula ORDER BY
        );

        while(c.moveToNext()){
            String nombre = c.getString(c.getColumnIndex("NOMBRE"));
            String semestre = c.getString(c.getColumnIndex("SEMESTRE"));
            String ciclo = c.getString(c.getColumnIndex("CICLO"));
            String estado = c.getString(c.getColumnIndex("ESTADO"));
            materias.add(new Materia(nombre,semestre,ciclo,estado));
        }
        db.close();
        return materias;
    }


    public List<Materia> getMateriasRestantes()
    {
        List<Materia> materias = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String columns[] = new String[]{"NOMBRE","SEMESTRE","CICLO","ESTADO"};
        Cursor c = db.query(
                "MATERIA",  // Nombre de la tabla
                columns,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                null  // Cláusula ORDER BY
        );

        while(c.moveToNext()){
            String estado = c.getString(c.getColumnIndex("ESTADO"));
            if(estado.equals("N"))
            {
                String nombre = c.getString(c.getColumnIndex("NOMBRE"));
                String semestre = c.getString(c.getColumnIndex("SEMESTRE"));
                String ciclo = c.getString(c.getColumnIndex("CICLO"));

                materias.add(new Materia(nombre,semestre,ciclo,estado));
            }

        }
        db.close();
        return materias;
    }




    public boolean changeState(Materia materia,boolean estado)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        if(estado==true)
        {
            values.put("ESTADO","S");
        }
        else {
            values.put("ESTADO","N");
        }

        sqLiteDatabase.update("MATERIA",values,"NOMBRE='"+materia.getNombre()+"'", null);
        sqLiteDatabase.close();
        return true;
    }

    public float getAvance()
    {
        int aux=0;
        float avance=0;
        List<Materia> materias =getMaterias();
        for(int i=0;i<materias.size();i++)
        {
            if(materias.get(i).estado.equals("S"))
                aux++;
        }
        avance=100*aux/50;
        return avance;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
