package com.example.buscabbva.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.buscabbva.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonBuscar;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referenceObjets();
    }

    private void referenceObjets() {
        spinner = findViewById(R.id.spinner);
        buttonBuscar = findViewById(R.id.button_buscar);
        buttonBuscar.setOnClickListener(this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getListATMorCaja());
        spinner.setAdapter(spinnerAdapter);
    }

    private List<String> getListATMorCaja() {
        List<String> opciones = new ArrayList<>();
        opciones.add("ATM");
        opciones.add("Sucursales");
        return opciones;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ResultadosActivity.class).putExtra("opcion", spinner.getSelectedItem().toString()));
    }
}
