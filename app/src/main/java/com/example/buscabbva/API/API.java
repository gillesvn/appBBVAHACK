package com.example.buscabbva.API;

import com.example.buscabbva.deserializers.MyDeserializer;
import com.example.buscabbva.modelos.ListaSucursales;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "http://10.251.70.209:5053/apimarketbranches/";
    private static Retrofit retrofit = null;

    //Esto es para cuando se usa el deserializador
    //Se instacia retrofit con la url base
    public static Retrofit getAPI() {
        if (retrofit == null) {
            GsonBuilder builder = new GsonBuilder().registerTypeAdapter(ListaSucursales.class, new MyDeserializer());
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))//para que el Gson haga la conversión de Json a java con el deserializador propio
                    .build();
        }
        return retrofit;
    }
}
