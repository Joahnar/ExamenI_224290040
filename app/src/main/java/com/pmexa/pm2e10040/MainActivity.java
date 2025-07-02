package com.pmexa.pm2e10040;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText Name, NumberPhone, Note;
    Spinner spinner;
    Button buttonSave;
    Button listContact;
    ImageView imageContact;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private Uri imageUri;

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

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Name1 = Name.getText().toString().trim();
                String Phone = NumberPhone.getText().toString().trim();
                String seleccion1 = spinner.getSelectedItem().toString();
                String codigoPais = extraerCodigo(seleccion1);
                String telefonoCompleto = codigoPais + Phone;
                String Note1 = Note.getText().toString().trim();
                imageContact = findViewById(R.id.imageContact);

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

        imageContact.setOnClickListener(v -> {
            mostrarDialogoImagen();
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

    private void mostrarDialogoImagen(){
        String[] opciones = {"Tomar foto", "Seleccionar desde galeria"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar imagen")
                .setItems(opciones, (dialog, which) -> {
                    if (which == 0){
                        abrirCamara();
                    }else{
                        abrirGaleria();
                    }
                })
                .show();
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                imageUri = data.getData();
                imageContact.setImageURI(imageUri);

            } else if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageContact.setImageBitmap(photo);
            }
        }
    }


}