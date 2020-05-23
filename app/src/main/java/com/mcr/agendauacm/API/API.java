package com.mcr.agendauacm.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String APIKEY = "bd50232510456be502c88a740e889814";
    //public static final String BASEURL ="https://api.openweathermap.org/data/2.5/";
    public static final String BASEURL ="https://artetextilmx.com/addons/";
    private static Retrofit retrofit=null;

    public static Retrofit getApi()
    {
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
