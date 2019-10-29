package com.example.buscabbva.API.APIServices;

import com.example.buscabbva.modelos.ListaSucursales;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BBVAService {
    @GET(" ")
    Call<ListaSucursales> getReport();
}


