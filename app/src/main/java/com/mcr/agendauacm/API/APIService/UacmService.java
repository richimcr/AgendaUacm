package com.mcr.agendauacm.API.APIService;

import com.mcr.agendauacm.model.City;
import com.mcr.agendauacm.model.Materia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UacmService {
    /*
    wheater es la base, q es el parametro que espera el nombre de la ciudad y se hace un segundo querey
    el cual lleva por nombre appid, ese parametro lo que espera es el key para poder ser utilizado
     */
    //api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
    //public Call<List<Materia>> getMaterias(@Query("q") String city,@Query("appkey") String key);

    @GET("prueba.php")
    //@GET("weather")
    public Call<List<Materia>> getMaterias(@Query("id") String id);
    //public Call<City> getMaterias(@Query("q") String city, @Query("appid") String key);


}
