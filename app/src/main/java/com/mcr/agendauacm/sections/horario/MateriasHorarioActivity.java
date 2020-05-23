package com.mcr.agendauacm.sections.horario;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mcr.agendauacm.R;
import com.mcr.agendauacm.model.HorarioDBHelper;
import com.mcr.agendauacm.model.HorarioMateria;
import com.mcr.agendauacm.model.Materia;
import com.mcr.agendauacm.model.MateriaDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MateriasHorarioActivity extends AppCompatActivity {

    private Button btAgregaMateria;
    private Drawable backgroundSeleccionado,backgroundDefault;
    private List<Button> botones;
    private Spinner spinnerHora;
    private ArrayAdapter<CharSequence> adapter;
    private Button btAnterior,btActual;
    private Spinner spinnerMateria;
    private MateriaDBHelper dbHelper;
    private List<String> listaMaterias;
    private ArrayAdapter<String> comboAdapter;
    private TextView tvMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias_horario);

        //obtiene la vista root para utilizar las funciones de la view
        View view = findViewById(android.R.id.content).getRootView();

        seleccionDia(view);

        inicializacion();

        agregarMateria();
    }


    /*
    findViewById(android.R.id.content).getRootView()
    Also it was reported that on some devices you have to use
    getWindow().getDecorView().findViewById(android.R.id.content)
     */

    //se encarga de inicializar y cambiar los botones de los dias
    private void seleccionDia(View view)
    {

        backgroundDefault=getDrawable(R.drawable.boton_dias);
        backgroundSeleccionado=getDrawable(R.drawable.boton_comun_background);

        Button btDia = (Button) view.findViewWithTag(""+10);
        btDia.setBackground(backgroundSeleccionado);
        btActual=(Button) view.findViewWithTag(""+10);

        for(int i=10;i<=14;i++)
        {
            btDia=(Button)view.findViewWithTag(""+i);
            btDia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btActual.setBackground(backgroundDefault);
                    btActual=(Button) v;
                    v.setBackground(backgroundSeleccionado);
                }
            });
        }
    }

    //almacena la materia en la base de datos local
    private void agregarMateria()
    {
        btAgregaMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                La materia se guarda empezando con el numero 20, siendo lunes
                los dos siguientes numeros hacen relacion a la posicion, la primera es el numero 0 al 9
                 */
                HorarioDBHelper dbHelper = new HorarioDBHelper(getApplicationContext());
                int idDia = Integer.valueOf(btActual.getTag().toString())+10;

                String id = ""+idDia+spinnerHora.getSelectedItemPosition();

                long resultado = dbHelper.insertaMateriaHorario(new HorarioMateria(id,spinnerMateria.getSelectedItem().toString()));
                if(resultado==-1)
                {
                    Toast.makeText(getApplicationContext(),"La materia se empalma",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Materia agregada correctamente",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //inicializa los elementos utlizados en la vista
    private void inicializacion()
    {
        dbHelper=new MateriaDBHelper(getApplicationContext());
        //llenado de materias para usarlas en el spinner
        List<Materia> materias = dbHelper.getMaterias();
        listaMaterias=new ArrayList<>();
        for(int i=0;i<materias.size();i++)
        {
            listaMaterias.add(materias.get(i).getNombre());
        }

        //inicializacion de elementos de la interfaz
        spinnerHora=(Spinner) findViewById(R.id.spinHora);
        spinnerMateria=(Spinner) findViewById(R.id.spinerMaterias);

        btAgregaMateria =(Button)findViewById(R.id.btGuardarMateria);
        adapter =ArrayAdapter.createFromResource(this,R.array.horas ,
                R.layout.spinner_layout);
        spinnerHora.setAdapter(adapter);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,listaMaterias);
        spinnerMateria.setAdapter(arrayAdapter);
    }

}
