package com.pmexa.pm2e10040;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText Name, NumberPhone, Note;
    Spinner spinner;
    Button buttonSave;
    Button listContact;
    ImageView imageContact;

    String[] countries = {
            "Honduras (+504)",
            "Guatemala (+502)",
            "El Salvador (+503)",
            "Costa Rica (+506)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.insertName);
        NumberPhone = findViewById(R.id.inNumberPhone);
        Note = findViewById(R.id.inNote);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                countries
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        buttonSave = findViewById(R.id.Save);
        listContact = findViewById(R.id.listSave);

        listContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
            startActivity(intent);
        });

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Name1 = Name.getText().toString().trim();
                String Phone = NumberPhone.getText().toString().trim();
                String seleccion1 = spinner.getSelectedItem().toString();
                String codigoPais = extraerCodigo(seleccion1);
                String telefonoCompleto = codigoPais + Phone;
                String Note1 = Note.getText().toString().trim();

                //Verificacion de Campos obligatorios
                if (Name1.isEmpty()){
                    Toast.makeText(MainActivity.this, "Debe escribir un nombre", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debe escribir un numero de telefono", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Note1.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debe escribir una nota", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(MainActivity.this, "Contacto Guardado", Toast.LENGTH_SHORT).show();
                }
            String seleccion = spinner.getSelectedItem().toString();
            Toast.makeText(MainActivity.this, "Contacto Guardado en " + seleccion1, Toast.LENGTH_SHORT).show();
            }

        });

    }

    private String extraerCodigo(String entrada) {
        int inicio = entrada.indexOf("(");
        int fin = entrada.indexOf(")");
        if (inicio != -1 && fin != -1 && fin > inicio) {
            return entrada.substring(inicio + 1, fin); // Extrae solo el +504
        } else {
            return ""; // Retorna vacío si no encuentra formato
        }
    }

}