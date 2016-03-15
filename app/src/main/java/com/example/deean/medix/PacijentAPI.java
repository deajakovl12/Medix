package com.example.deean.medix;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
/**
 * Created by Deean on 21.2.2016..
 */
public interface PacijentAPI {
    String BASE_URL = "http://jaka12.heliohost.org";
    @GET("/dohvati_pacijente_doktora.php") Call<ArrayList<Pacijent>> response();
    class Factory{
        private static PacijentAPI service;
        public static PacijentAPI getIstance(){
            if(service==null){
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(PacijentAPI.class);
                return service;
            }
            else{
                return service;
            }
        }
    }
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build();
    PacijentAPI service = retrofit.create(PacijentAPI.class);
}
