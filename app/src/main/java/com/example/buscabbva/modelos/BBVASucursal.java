package com.example.buscabbva.modelos;

public class BBVASucursal {
    private String nombreSucursal;
    private String direccionSucursal;
    private String telefonoSucursal;
    private String coordenadas;

    public BBVASucursal(String nombreSucursal, String direccionSucursal, String telefonoSucursal, String coordenadas) {
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
        this.telefonoSucursal = telefonoSucursal;
        this.coordenadas = coordenadas;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }


    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public String getCoordenadas() {
        return coordenadas;
    }
}
