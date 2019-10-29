package com.example.buscabbva.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaSucursales {
    @SerializedName("Brand")
    private List<BBVASucursal> bbvaSucursalList;

    public ListaSucursales(List<BBVASucursal> bbvaSucursalList) {
        this.bbvaSucursalList = bbvaSucursalList;
    }

    public List<BBVASucursal> getBbvaSucursalList() {
        return bbvaSucursalList;
    }
}
