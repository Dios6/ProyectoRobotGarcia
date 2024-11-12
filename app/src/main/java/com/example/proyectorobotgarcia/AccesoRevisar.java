package com.example.proyectorobotgarcia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class AccesoRevisar extends AppCompatActivity {

    Spinner spSpinner;
    String[] rol = new String[]{"Tester", "Jugador"};
    EditText edtNom;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisar);

        edtNom = (EditText) findViewById(R.id.edtNom);
        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        lista = (ListView) findViewById(R.id.lstLista);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rol);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
        CargarLista();

        findViewById(R.id.botonGPS).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoRevisar.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoRevisar.this, AccesoGPS.class);
                        startActivity(intent);
                    }
                });
            }
        });

        findViewById(R.id.botonVol).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoRevisar.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoRevisar.this, AccesoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void CargarLista() {
        DataHelper dh = new DataHelper(this, "registros.db", null, 2);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select nombre, rol from registros", null);
        String[] arr = new String[c.getCount()];
        if (c.moveToFirst() == true) {
            int i = 0;
            do {
                String linea = "||" + c.getString(0) + "||" + c.getString(1) + "||";
                arr[i] = linea;
                i++;
            } while (c.moveToNext() == true);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);
            bd.close();
        }
    }

    public void onClickAgregar (View view){
        DataHelper dh=new DataHelper(this, "registros.db", null, 2);
        SQLiteDatabase bd= dh.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("nombre", edtNom.getText().toString());
        reg.put("rol", spSpinner.getSelectedItem().toString());
        long resp = bd.insert("registros", null, reg);
        bd.close();
        if(resp==-1){
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Registro Agregado", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }

    public void limpiar(){
        edtNom.setText("");
    }
    public void onClickModificar(View view){
        DataHelper dh = new DataHelper(this, "registros.db", null, 2);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("nombre", edtNom.getText().toString());
        reg.put("rol", spSpinner.getSelectedItem().toString());
        long respuesta = bd.update("registros", reg, "nombre=?", new String[]{edtNom.getText().toString()});
        bd.close();
        if (respuesta == -1){
            Toast.makeText(this,"Dato No Modificado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Dato Modificado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }
    public void onClickEliminar(View view){
        DataHelper dh = new DataHelper(this, "registros.db", null, 2);
        SQLiteDatabase bd = dh.getWritableDatabase();
        String NomEliminar = edtNom.getText().toString();
        long respuesta = bd.delete("registros", "nombre=?", new String[]{NomEliminar});
        bd.close();
        if (respuesta == -1){
            Toast.makeText(this,"Dato No Eliminado", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Dato Eliminado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }
}
