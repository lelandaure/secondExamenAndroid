package com.example.dam1_cl2_stephanyrojasalejandro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements View.OnClickListener {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String,String>> listaPacientes = db.obtenerPaciente();
        if (!listaPacientes.isEmpty()){
            ListView lv = (ListView)findViewById(R.id.lvPacientes);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                    listaPacientes, R.layout.lista_row,
                    new String[]{"dni","nombres","fecha"},
                    new int[]{R.id.dni, R.id.nombre,R.id.fecha});
            lv.setAdapter(adapter);
        }
        Button btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);
        Button btnNuevo = (Button)findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBuscar:
                EditText txt_Nombre=(EditText)findViewById(R.id.txtNombre);
                DbHandler db = new DbHandler(this);
                ArrayList<HashMap<String,String>>listaPacientes = db.obtenerPacientePorNombre(txt_Nombre.getText().toString());
                ListView lv = (ListView)findViewById(R.id.lvPacientes);
                ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                        listaPacientes, R.layout.lista_row,
                        new String[]{"dni","nombres","fecha"},
                        new int[]{R.id.dni, R.id.nombre,R.id.fecha});
                lv.setAdapter(adapter);
                break;
            case R.id.btnNuevo:
                intent = new Intent(MainActivity.this,
                        PacienteActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void salir(View view) {
        finish();
        System.exit(0);
    }
}