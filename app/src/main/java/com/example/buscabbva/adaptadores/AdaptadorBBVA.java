package com.example.buscabbva.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buscabbva.R;
import com.example.buscabbva.activities.MapsActivity;
import com.example.buscabbva.modelos.BBVASucursal;

import java.util.List;

public class AdaptadorBBVA extends RecyclerView.Adapter<AdaptadorBBVA.ViewHolder> {
    private Context context;
    private Activity activity;
    private int layout;
    private List<BBVASucursal> bbvaSucursalList;
    private OnItemClickListener listener;

    public AdaptadorBBVA(Context context, Activity activity, int layout, List<BBVASucursal> bbvaSucursalList, OnItemClickListener listener) {
        this.context = context;
        this.activity = activity;
        this.layout = layout;
        this.bbvaSucursalList = bbvaSucursalList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(bbvaSucursalList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return bbvaSucursalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private TextView textViewNombreSucursal;
        private TextView textViewDireccion;
        private TextView textViewTelefono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreSucursal = itemView.findViewById(R.id.text_view_nombre_sucursal);
            textViewDireccion = itemView.findViewById(R.id.text_view_direccion);
            textViewTelefono = itemView.findViewById(R.id.text_view_telefono);
        }

        public void bind(final BBVASucursal sucursal, final OnItemClickListener listener) {
            textViewNombreSucursal.setText(sucursal.getNombreSucursal());
            textViewDireccion.setText(sucursal.getDireccionSucursal());
            textViewTelefono.setText(sucursal.getTelefonoSucursal());
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(sucursal, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            activity.getMenuInflater().inflate(R.menu.menu_context_menu, menu);
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_open_maps:
                    //TODO abrir maps
                    //Toast.makeText(context, bbvaSucursalList.get(getAdapterPosition()).getCoordenadas(), Toast.LENGTH_LONG).show();
                    activity.startActivity(new Intent(context, MapsActivity.class));
                    return true;

            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onClickItem(BBVASucursal sucursal, int position);
    }
}