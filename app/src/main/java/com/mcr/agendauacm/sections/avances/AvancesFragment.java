package com.mcr.agendauacm.sections.avances;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mcr.agendauacm.API.API;
import com.mcr.agendauacm.API.APIService.UacmService;
import com.mcr.agendauacm.R;
import com.mcr.agendauacm.model.City;
import com.mcr.agendauacm.model.Materia;
import com.mcr.agendauacm.model.MateriaDBHelper;
import com.mcr.agendauacm.model.ParseJson;
import com.mcr.agendauacm.ui.main.SectionsPagerAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AvancesFragment extends Fragment {

    private TextView tvPorcentaje;
    private MateriaDBHelper dbHelper;
    private TextView tvMateriasRestantes;
    private RecyclerView recyclerMateriasFaltantes;
    private AdapterRestante adapterRestante;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedId;
    private Button btMateriaCertificada;
    private String id;
    private LottieAnimationView animNivel;



    public static AvancesFragment newInstance() {
        return new AvancesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View  view = inflater.inflate(R.layout.avances_fragment, container, false);
        inicializacion(view);

        sharedId= this.getActivity().getSharedPreferences("carrera", Context.MODE_PRIVATE);
        id=sharedId.getString("id","sinId");
        if(id.equals("sinId"))
        {
            Toast.makeText(getContext(),"Regresa a la pagina de inicio y selecciona una carrera",Toast.LENGTH_LONG).show();
        }
        else
        {
            //seccion para descargar y guardar en base de datos las materias obtenidas por el WebService
            List<Materia> listaMaterias= new ArrayList<>();
            dbHelper = new MateriaDBHelper(getContext());
            listaMaterias = dbHelper.getMaterias();
            if(listaMaterias.size()==0)
            {
                materiasWebService(id,dbHelper);
            }
        }
        return view;
    }

    //funcion que imprime una animacion aleatoriamente
    private void  setAnimacion() {
        Random aleatorio = new Random(System.currentTimeMillis());
        int aux = aleatorio.nextInt(7)+1;
        animNivel.setImageAssetsFolder("assets");
        animNivel.setAnimation("" + aux + ".json");
        /*
        1 manzana
        2 flor
        3 lampara
        4 perro azul
        5 buho
        6 agua
        7 ajolote
         */
    }

    @Override
    public void onResume() {
        super.onResume();
        dbHelper= new MateriaDBHelper(getContext());

        Float porcentajeAvance = dbHelper.getAvance();
        tvPorcentaje.setText(""+porcentajeAvance+"%");
        setAnimacion();

        if(porcentajeAvance>=70&&porcentajeAvance<=100)
        {

        }
        else if(porcentajeAvance<70&&porcentajeAvance>=50)
        {

        }
        else if(porcentajeAvance<50&&porcentajeAvance>=0)
        {

        }

        adapterRestante = new AdapterRestante(dbHelper.getMateriasRestantes(),R.layout.materias_restantes_layout);
        recyclerMateriasFaltantes.setLayoutManager(layoutManager);
        recyclerMateriasFaltantes.setAdapter(adapterRestante);

        dbHelper.close();
    }



    private void inicializacion(View view)
    {
        tvPorcentaje =(TextView) view.findViewById(R.id.tvPorcentaje);

        animNivel=(LottieAnimationView)view.findViewById(R.id.lottieNivel);

        recyclerMateriasFaltantes=(RecyclerView) view.findViewById(R.id.recyclerFaltantes);
        layoutManager = new LinearLayoutManager(getContext());
        btMateriaCertificada = (Button) view.findViewById(R.id.btAgregaMateriaAprobada);
        btMateriaCertificada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),GestionAvanceActivity.class);
                startActivity(intent);
            }
        });
                //getActivity().getSupportFragmentManager().beginTransaction()
                //      .replace(R.id.avancesLayout,materiasFragment).commit();


                //setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


    private void materiasWebService(String id,MateriaDBHelper dbHelper)
    {
        UacmService service = API.getApi().create(UacmService.class);
        Call<List<Materia>> materiasCall =  service.getMaterias(id);
        materiasCall.enqueue(new Callback<List<Materia>>() {
            @Override
            public void onResponse(Call<List<Materia>> call, Response<List<Materia>> response) {
                dbHelper.insertaMaterias(response.body());
                Toast.makeText(getContext(),"Plan de estudios descargado.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Materia>> call, Throwable t) {
                Toast.makeText(getContext(),"Error de conexión con el servidor.",Toast.LENGTH_LONG).show();
            }
        });
    }

}
