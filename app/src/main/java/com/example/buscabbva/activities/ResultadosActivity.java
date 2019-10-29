package com.example.buscabbva.activities;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buscabbva.API.API;
import com.example.buscabbva.API.APIServices.BBVAService;
import com.example.buscabbva.R;
import com.example.buscabbva.adaptadores.AdaptadorBBVA;
import com.example.buscabbva.modelos.BBVASucursal;
import com.example.buscabbva.modelos.ListaSucursales;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class ResultadosActivity extends AppCompatActivity implements AdaptadorBBVA.OnItemClickListener {
    private List<BBVASucursal> sucursalList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String opcion;
    private final String OPCION_SUCURSALES = "Sucursales";
    private final String OPCION_ATM = "ATM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        setToolBar();
        referenceObjects();
        getExtras();
        //httpRequest();
    }

    private void httpRequest() {
        BBVAService service = API.getAPI().create(BBVAService.class);
        Call<ListaSucursales> sucursalesCall;

        sucursalesCall = service.getReport();
        sucursalesCall.enqueue(
                new Callback<ListaSucursales>() {
                    @Override
                    public void onResponse(Call<ListaSucursales> call, Response<ListaSucursales> response) {
                        sucursalList.addAll(response.body().getBbvaSucursalList());
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<ListaSucursales> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Valio", LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void setToolBar() {
        getSupportActionBar().setTitle("Sucursales BBVA");
    }

    private void referenceObjects() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        if (isWiFiEnabled()) {
            sucursalList = getSucursalList();
        } else {
            sucursalList = new ArrayList<>();
        }

        adapter = new AdaptadorBBVA(this, this, R.layout.recycler_view_item, sucursalList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<BBVASucursal> getSucursalList() {
        ArrayList<BBVASucursal> lista = new ArrayList<>();
        lista.add(new BBVASucursal("Diana", "Av. Paseo de la Reforma 403, Cuauhtémoc, 06500 Ciudad de México, CDMX", "5552262663", "19.4236107,-99.1825254"));
        lista.add(new BBVASucursal("Glorieta Diana", "Av. Paseo de la Reforma 403, Cuauhtémoc, 06500 Ciudad de México, CDMX", "5552262663", "19.4236107,-99.1825254"));
        lista.add(new BBVASucursal("Parque VIA", "RIO AMUR  5 06500 CUAUHTEMOC DISTRITO FEDERAL", " 5552262668", "19.4193404,-99.1788412"));
        return lista;
    }

    private void getExtras() {
        if (!getIntent().getExtras().isEmpty()) {
            opcion = getIntent().getExtras().getString("opcion");
            //Toast.makeText(this, opcion, LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickItem(BBVASucursal sucursal, int position) {

    }

    private boolean isWiFiEnabled() {
        //Preguntar por el Wifi
        try {
            return (Settings.Global.getInt(getContentResolver(), Settings.Global.WIFI_ON)) != 0;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }


}
