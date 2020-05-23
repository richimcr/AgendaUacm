package com.mcr.agendauacm.sections.avances;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mcr.agendauacm.R;
import com.mcr.agendauacm.model.Materia;
import com.mcr.agendauacm.model.MateriaDBHelper;

import java.util.List;

public class GestionAvanceActivity extends AppCompatActivity {

    RecyclerView recyclerSeleccion;
    AdapterMaterias adapter;
    RecyclerView.LayoutManager layoutManager;
    MateriaDBHelper dbHelper;
    List<Materia> materiaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_avance);
        recyclerSeleccion =(RecyclerView)findViewById(R.id.recyclerGestionAvance);
        layoutManager = new LinearLayoutManager(this);
        dbHelper = new MateriaDBHelper(this);
        materiaList=dbHelper.getMaterias();

        adapter = new AdapterMaterias(materiaList, R.layout.seleccion_materias_layout, new AdapterMaterias.IonItemClickListener() {
            @Override
            public void onItemChangeState(Materia materia, int position, boolean estado) {
                dbHelper.changeState(materia,estado);
                if(materiaList.get(position).getEstado().equals("S"))
                {
                    materiaList.get(position).setEstado("N");
                }
                else if(materiaList.get(position).getEstado().equals("N"))
                {
                    materiaList.get(position).setEstado("S");
                }

                adapter.notifyDataSetChanged();
            }
        });

        recyclerSeleccion.setLayoutManager(layoutManager);
        recyclerSeleccion.setAdapter(adapter);

    }
}
