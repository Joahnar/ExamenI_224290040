package com.pmexa.pm2e10040;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> listaCompleta;
    private List<Contact> listaFiltrada;

    public ContactAdapter(List<Contact> contactos) {
        this.listaCompleta = contactos;
        this.listaFiltrada = new ArrayList<>(contactos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, telefono, nota;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.insertName);
            telefono = itemView.findViewById(R.id.inNumberPhone);
            nota = itemView.findViewById(R.id.inNote);
        }
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_list, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contacto = listaFiltrada.get(position);
        holder.nombre.setText(contacto.getNombre());
        holder.telefono.setText(contacto.getTelefono());
        holder.nota.setText(contacto.getNotas());
    }

    @Override
    public int getItemCount() {
        return listaFiltrada.size();
    }

    public void filtrar(String texto) {
        listaFiltrada.clear();
        if (texto.isEmpty()) {
            listaFiltrada.addAll(listaCompleta);
        } else {
            for (Contact c : listaCompleta) {
                if (c.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }
}