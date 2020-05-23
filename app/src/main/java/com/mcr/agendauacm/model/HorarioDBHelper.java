package com.mcr.agendauacm.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HorarioDBHelper extends SQLiteOpenHelper {


    public HorarioDBHelper(Context context) {
        super(context, "uacmhorario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablaExec = "CREATE TABLE HORARIO (" +
                "ID TEXT PRIMARY KEY,"+
                "NOMBRE TEXT NOT NULL)";
        db.execSQL(tablaExec);
    }

    public long insertaMateriaHorario(HorarioMateria materia)
    {
        long resultado = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID",materia.getId());
        values.put("NOMBRE",materia.getNombre());
        resultado=sqLiteDatabase.insert("HORARIO",null,values);
        sqLiteDatabase.close();
        return resultado;
    }

    public boolean eliminaMateria(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("HORARIO", "ID" + "=" + id, null) > 0;
    }


    public List<HorarioMateria> getHorario()
    {
        List<HorarioMateria> materias = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String columns[] = new String[]{"ID","NOMBRE"};
        Cursor c = db.query(
                "HORARIO",  // Nombre de la tabla
                columns,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                null  // Cláusula ORDER BY
        );

        while(c.moveToNext()){
            String id = c.getString(c.getColumnIndex("ID"));
            String nombre = c.getString(c.getColumnIndex("NOMBRE"));
            materias.add(new HorarioMateria(id,nombre));
        }
        db.close();
        return materias;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
