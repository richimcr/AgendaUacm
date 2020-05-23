package com.mcr.agendauacm.sections.horario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mcr.agendauacm.R;
import com.mcr.agendauacm.model.HorarioDBHelper;
import com.mcr.agendauacm.model.HorarioMateria;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class HorarioFragment extends Fragment {

    private Button btLunes,btMartes,btMiercoles,btJueves,btViernes;
    private TimeZone timezone;
    private Calendar calendar;
    private Button btAgregarMateria;
    private HorarioDBHelper dbHelper;
    private List<HorarioMateria> materias;
    private Button btActual;
    private Drawable backgroundDefault;
    private ArrayList<Button> btDias;
    private Drawable backgroudSeleccionado;
    private MediaPlayer mediaPlayer;

  public static HorarioFragment newInstance() {
        return new HorarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      View view =inflater.inflate(R.layout.horario_fragment, container, false);
      dbHelper=new HorarioDBHelper(getContext());

      inicializacion(view);

      cambioDiasHorario(view);

      editarMateria(view);

     // seleccionHorario(view);

      //cambioMateria(view);


      //abre la vista para agregar materias
      btAgregarMateria.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getContext(),MateriasHorarioActivity.class);
              startActivity(intent);
          }
      });

      return view;

  }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void seleccionHorario(final View view)
    {
        btLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos(view);
                btActual.setBackground(backgroundDefault);
                btActual=btLunes;
                btLunes.setBackground(getResources().getDrawable(R.drawable.boton_comun_background));
                for(int i=0;i<materias.size();i++)
                {
                    if(materias.get(i).getId().contains("L"))
                    {
                        String hora = materias.get(i).getId().substring(1);
                        TextView textView = (TextView) view.findViewWithTag(hora);
                        textView.setText(materias.get(i).getNombre());
                    }
                }
            }
        });

        btMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos(view);
                btActual.setBackground(backgroundDefault);
                btActual=btMartes;
                btMartes.setBackground(getResources().getDrawable(R.drawable.boton_comun_background));
                for(int i=0;i<materias.size();i++)
                {
                    if(materias.get(i).getId().contains("M"))
                    {
                        String hora = materias.get(i).getId().substring(1);
                        TextView textView = (TextView) view.findViewWithTag(hora);
                        textView.setText(materias.get(i).getNombre());
                    }
                }
            }
        });

        btMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos(view);
                btActual.setBackground(backgroundDefault);
                btActual=btMiercoles;
                btMiercoles.setBackground(getResources().getDrawable(R.drawable.boton_comun_background));
                for(int i=0;i<materias.size();i++)
                {
                    if(materias.get(i).getId().contains("X"))
                    {
                        String hora = materias.get(i).getId().substring(1);
                        TextView textView = (TextView) view.findViewWithTag(hora);
                        textView.setText(materias.get(i).getNombre());
                    }
                }
            }
        });

        btJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos(view);
                btActual.setBackground(backgroundDefault);
                btActual=btJueves;
                btJueves.setBackground(getResources().getDrawable(R.drawable.boton_comun_background));
                for(int i=0;i<materias.size();i++)
                {
                    if(materias.get(i).getId().contains("J"))
                    {
                        String hora = materias.get(i).getId().substring(1);
                        TextView textView = (TextView) view.findViewWithTag(hora);
                        textView.setText(materias.get(i).getNombre());
                    }
                }
            }
        });

        btViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos(view);
                btActual.setBackground(backgroundDefault);
                btActual=btViernes;
                btViernes.setBackground(getResources().getDrawable(R.drawable.boton_comun_background));
                for(int i=0;i<materias.size();i++)
                {
                    if(materias.get(i).getId().contains("V"))
                    {
                        String hora = materias.get(i).getId().substring(1);
                        TextView textView = (TextView) view.findViewWithTag(hora);
                        textView.setText(materias.get(i).getNombre());
                    }
                }
            }
        });
    }

    private void inicializacion(View view)
    {
        materias=dbHelper.getHorario();
        btAgregarMateria= (Button) view.findViewById(R.id.btAbrirHorario);
        backgroundDefault=  getResources().getDrawable(R.drawable.boton_dias);
        backgroudSeleccionado = getResources().getDrawable(R.drawable.boton_comun_background);

    }

    //limpia todos los campos de las materias
    private void limpiarCampos(View view)
    {
        for(int i=0;i<10;i++)
        {
            TextView textView = (TextView)view.findViewWithTag(""+i);
            textView.setText("");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        materias = dbHelper.getHorario();
        for(int i=0;i<materias.size();i++)
        {
            if(materias.get(i).getId().substring(0,2).equals(btActual.getTag().toString()))
            {
                String hora = materias.get(i).getId().substring(2,3);
                TextView textView = (TextView) super.getView().findViewWithTag(hora);
                textView.setText(materias.get(i).getNombre());
            }
        }



        /*
        EL DIA LUNES EMPIEZA EN EL NUMERO 2
         */

        /*
        El id esta conformado por los numero del 10 al 14 para el dia
        el siguiente esta formado por el tag de la etiqueta, esta inicia con el
        numero 0 y acaba en el numero 9
         */


        /*
        String tag = String.valueOf(aux);
        for(int i=0;i<materias.size();i++)
        {
            if (materias.get(i).getId().substring(0, 2).equals(tag))
            {
                String hora = materias.get(i).getId().substring(2,3);
                TextView textView = (TextView) super.getView().findViewWithTag(hora);
                textView.setText(materias.get(i).getNombre());
            }
        }
         */
    }

    private void editarMateria(View view)
    {
        for(int i=0;i<=9;i++)
        {
            TextView textView= (TextView) view.findViewWithTag(""+i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!textView.getText().toString().equals(""))
                    {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("¿Deseas eliminar esta materia?")
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        eliminarMateria(v);
                                        TextView campo =(TextView)v;
                                        campo.setText("");
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                    }
                                });
                        builder.show();

                    }
                }
            });
        }
    }

    private void eliminarMateria(View campo)
    {
        String id="";
        switch (btActual.getText().toString())
        {
            case "L":
                id="20"+campo.getTag().toString();
                break;
            case "M":
                id="21"+campo.getTag().toString();
                break;
            case "X":
                id="22"+campo.getTag().toString();
                break;
            case "J":
                id="23"+campo.getTag().toString();
                break;
            case "V":
                id="24"+campo.getTag().toString();
                break;
        }

        dbHelper.eliminaMateria(id);
        materias=dbHelper.getHorario();
        mediaPlayer = MediaPlayer.create(getContext(),R.raw.borrar);
        mediaPlayer.start();
        Toast.makeText(getContext(),"Materia eliminada",Toast.LENGTH_SHORT).show();
    }


    /*
    A los botones se les coloco una tag, la base es bt
    el siguente es un digito que va del 1 al 5 haciendo referencia
    a los dias de le semana
     */
    private void cambioDiasHorario(final View view)
    {
        btDias = new ArrayList<>();
        for(int i=20;i<=24;i++)
        {
            Button btDia = (Button) view.findViewWithTag(""+i);
            btDia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    limpiarCampos(view);
                    btActual.setBackground(backgroundDefault);
                    btActual=(Button) v;
                    v.setBackground(backgroudSeleccionado);

                    for(int i=0;i<materias.size();i++)
                    {
                        if(materias.get(i).getId().substring(0,2).equals(btActual.getTag().toString()))
                        {
                            String hora = materias.get(i).getId().substring(2,3);
                            TextView textView = (TextView)  view.findViewWithTag(hora);
                            textView.setText(materias.get(i).getNombre());
                        }
                    }

                }
            });
        }

        timezone = TimeZone.getDefault();
        calendar = new GregorianCalendar(timezone);
        int dia =calendar.get(Calendar.DAY_OF_WEEK)+18;

        if(dia>=20&&dia<=24)
        {
            btActual= (Button) view.findViewWithTag(""+dia);
            btActual.setBackground(backgroudSeleccionado);
        }
        else
        {
            btActual= (Button) view.findViewWithTag(""+20);
            btActual.setBackground(backgroudSeleccionado);
        }
    }


}
