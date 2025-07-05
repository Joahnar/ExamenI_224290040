package com.pmexa.pm2e10040;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    RecyclerView recyclerContactos;
    EditText searchBox;
    ContactAdapter adapter; // la crearemos después
    List<Contact> listaContactos = new ArrayList<>(); // modelo Contact

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        searchBox = findViewById(R.id.searchBox);
        recyclerContactos = findViewById(R.id.recyclerContactos);

        recyclerContactos.setLayoutManager(new LinearLayoutManager(this));

        // Aquí debería recuperar los contactos desde tu base de datos o lista temporal
        listaContactos = obtenerContactosFicticios(); // temporal

        adapter = new ContactAdapter(listaContactos);
        recyclerContactos.setAdapter(adapter);

        // Filtro en tiempo real
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private List<Contact> obtenerContactosFicticios() {
        List<Contact> contactos = new ArrayList<>();
        contactos.add(new Contact("Ana", "+50498765432", "Amiga"));
        contactos.add(new Contact("Pedro", "+50378945612", "Trabajo"));
        return contactos;
    }

}


