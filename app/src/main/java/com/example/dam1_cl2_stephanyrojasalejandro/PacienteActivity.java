package com.example.dam1_cl2_stephanyrojasalejandro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PacienteActivity extends Activity implements View.OnClickListener {

    EditText txt_dni, txt_nombres, txt_motivo, txt_doctor, txt_costo, txt_fecha;
    Button btn_grabar, btn_cerrar;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        txt_dni = (EditText)findViewById(R.id.txtDNI);
        txt_nombres = (EditText)findViewById(R.id.txtNombres);
        txt_motivo = (EditText)findViewById(R.id.txtMotivo);
        txt_doctor = (EditText)findViewById(R.id.txtDoctor);
        txt_costo = (EditText)findViewById(R.id.txtCosto);
        txt_fecha = (EditText)findViewById(R.id.txtFecha);
        btn_grabar = (Button)findViewById(R.id.btnGrabar);
        btn_grabar.setOnClickListener(this);
        btn_cerrar = (Button)findViewById(R.id.btnCerrar);
        btn_cerrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGrabar:
                String dni = txt_dni.getText().toString();
                String nombres = txt_nombres.getText().toString();
                String motivo = txt_motivo.getText().toString();
                String doctor = txt_doctor.getText().toString();
                Double costo = Double.valueOf(txt_costo.getText().toString());
                String fecha = txt_fecha.getText().toString();
                DbHandler dbHandler = new DbHandler(PacienteActivity.this);
                dbHandler.insertarPaciente(dni,nombres,motivo,doctor,costo,fecha);
                intent = new Intent(PacienteActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"El paciente fue registrado",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCerrar:
                intent = new Intent(PacienteActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
