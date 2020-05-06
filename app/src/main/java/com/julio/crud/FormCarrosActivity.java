package com.julio.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.julio.crud.bd.CarrosBD;
import com.julio.crud.model.Carros;

public class FormCarrosActivity extends AppCompatActivity {

    EditText editText_modelo, editText_placa, editText_cor;
    Button btnEditar;
    Carros editarCarro, carro;
    CarrosBD carrosBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_carros);

        carro = new Carros();

        carrosBD = new CarrosBD(FormCarrosActivity.this);

        Intent intent = getIntent();
        editarCarro = (Carros) intent.getSerializableExtra("carro-escolhido");

        editText_modelo = (EditText) findViewById(R.id.editText_modelo);
        editText_placa = (EditText) findViewById(R.id.editText_placa);
        editText_cor = (EditText) findViewById(R.id.editText_cor);

        btnEditar = (Button) findViewById(R.id.btn_editar);

        if(editarCarro != null){
            btnEditar.setText("Editar");

            editText_modelo.setText(editarCarro.getModelo());
            editText_placa.setText(editarCarro.getPlaca());
            editText_cor.setText(editarCarro.getCor());

            carro.setId(editarCarro.getId());
        }else {
            btnEditar.setText("Cadastrar");
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carro.setModelo(editText_modelo.getText().toString());
                carro.setPlaca(editText_placa.getText().toString());
                carro.setCor(editText_cor.getText().toString());
                if(btnEditar.getText().toString().equals("Cadastrar")){
                    carrosBD.salvarCarro(carro);
                    carrosBD.close();
                }else{
                    carrosBD.alterarCarro(carro);
                    carrosBD.close();
                }
            }
        });
    }
}
