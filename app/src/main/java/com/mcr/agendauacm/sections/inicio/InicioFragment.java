package com.mcr.agendauacm.sections.inicio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mcr.agendauacm.R;

public class InicioFragment extends Fragment {

    private SharedPreferences sharedCarrera;
    private SharedPreferences sharedId;
    private String carrera;
    private TextView tvLicenciatura;
    private Button btAgregaMateria;

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        sharedCarrera= this.getActivity().getSharedPreferences("carrera", Context.MODE_PRIVATE);
        sharedId = this.getActivity().getSharedPreferences("id",Context.MODE_PRIVATE);
        carrera=sharedCarrera.getString("carrera","sinCarrera");
        View view = inflater.inflate(R.layout.inicio_fragment, container, false);
        tvLicenciatura =view.findViewById(R.id.tvLicenciatura);

        btAgregaMateria=(Button) view.findViewById(R.id.btGuardarMateria);

        if(carrera.equals("sinCarrera"))
        {
                getColegio();
        }
        else
        {
            tvLicenciatura.setText(carrera);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getColegio()
    {
        AlertDialog.Builder builderColegio = new AlertDialog.Builder(getContext());
        builderColegio.setTitle("Selecciona tu colegio").setItems(R.array.colegios, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getCarrera(which);
            }
        });
        AlertDialog dialogColegio = builderColegio.create();
        dialogColegio.show();
    }

    private void getCarrera(int selectColegio)
    {
        AlertDialog.Builder builderCarrera = new AlertDialog.Builder(getContext());
        builderCarrera.setTitle("Selecciona tu carrera");

        SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences("carrera", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        switch (selectColegio)
        {
            case 0:
                builderCarrera.setItems(R.array.humanidades_ciencias, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        which+=200;
                        switch (which)
                        {
                            case 200:
                                editor.putString("carrera","Arte y Patrimonio Cultural");
                                editor.putString("id",""+which);
                                carrera="Arte y Patrimonio Cultural";
                                break;
                            case 201:
                                editor.putString("carrera","Ciencia Política y Administración Urbana");
                                editor.putString("id",""+which);
                                carrera="Ciencia Política y Administración Urbana";
                                break;
                            case 202:
                                editor.putString("carrera","Ciencias Sociales");
                                editor.putString("id",""+which);
                                carrera="Ciencias Sociales";
                                break;
                            case 203:
                                editor.putString("carrera","Comunicación y Cultura");
                                editor.putString("id",""+which);
                                carrera="Comunicación y Cultura";
                                break;
                            case 204:
                                editor.putString("carrera","Creación Literaria");
                                editor.putString("id",""+which);
                                carrera="Creación Literaria";
                                break;
                            case 205:
                                editor.putString("carrera","Historia y Sociedad Contemporánea");
                                editor.putString("id",""+which);
                                carrera="Historia y Sociedad Contemporánea";
                                break;
                            case 206:
                                editor.putString("carrera","Filosofía e Historia de las Ideas");
                                editor.putString("id",""+which);
                                carrera="Filosofía e Historia de las Ideas";
                                break;
                            case 207:
                                editor.putString("carrera","Derecho");
                                editor.putString("id",""+which);
                                carrera="Derecho";
                                break;
                        }
                        editor.commit();
                    }
                });
                break;
            case 1:
                builderCarrera.setItems(R.array.ciencias_humanidades, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        which+=100;
                        switch (which)
                        {
                            case 100:
                                editor.putString("carrera","Ciencias Ambientales y Cambio Climático");
                                editor.putString("id",""+which);
                                carrera="Ciencias Ambientales y Cambio Climático";
                                break;
                            case 101:
                                editor.putString("carrera","Nutrición y Salud");
                                editor.putString("id",""+which);
                                carrera="Nutrición y Salud";
                                break;
                            case 102:
                                editor.putString("carrera","Promoción de la Salud");
                                editor.putString("id",""+which);
                                carrera="Promoción de la Salud";
                                break;
                            case 103:
                                editor.putString("carrera","Protección Civil y Gestión de Riesgos");
                                editor.putString("id",""+which);
                                carrera="Protección Civil y Gestión de Riesgos";
                                break;
                        }
                        editor.commit();
                    }
                });
                break;
            case 2:
                builderCarrera.setItems(R.array.ciencia_tecnologia, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                editor.putString("carrera","Ciencias Genómicas");
                                editor.putString("id",""+which);
                                carrera="Ciencias Genómicas";
                                break;
                            case 1:
                                editor.putString("carrera","Ingeniería en Sistemas de Transporte Urbano");
                                editor.putString("id",""+which);
                                carrera="Ingeniería en Sistemas de Transporte Urbano";
                                break;
                            case 2:
                                editor.putString("carrera","Ingeniería en Sistemas Electrónicos Industriales");
                                editor.putString("id",""+which);
                                carrera="Ingeniería en Sistemas Electrónicos Industriales";
                                break;
                            case 3:
                                editor.putString("carrera","Licenciatura en Ingeniería de Software");
                                editor.putString("id",""+which);
                                carrera="Licenciatura en Ingeniería de Software";
                                break;
                            case 4:
                                editor.putString("carrera","Ingeniería en Sistemas Electrónicos y de Telecomunicaciones");
                                editor.putString("id",""+which);
                                carrera="Ingeniería en Sistemas Electrónicos y de Telecomunicaciones";
                                break;
                            case 5:
                                editor.putString("carrera","Ingeniería en Sistemas Energéticos");
                                editor.putString("id",""+which);
                                carrera="Ingeniería en Sistemas Energéticos";
                                break;
                            case 6:
                                editor.putString("carrera","Modelación Matemática");
                                editor.putString("id",""+which);
                                carrera="Modelación Matemática";
                                break;
                        }
                        editor.commit();
                    }
                });
                break;
        }

        builderCarrera.show();
    }

}
