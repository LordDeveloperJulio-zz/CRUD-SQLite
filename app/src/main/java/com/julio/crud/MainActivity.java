package com.julio.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.julio.crud.bd.CarrosBD;
import com.julio.crud.model.Carros;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    CarrosBD carrosBD;
    ArrayList<Carros> carroLista;
    Carros carro;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);

        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FormCarrosActivity.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.carroLista);

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Carros carroEscolhido = (Carros) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormCarrosActivity.class);
                i.putExtra("carro-escolhido", carroEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                carro = (Carros) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Excluir");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                carrosBD = new CarrosBD((MainActivity.this));
                carrosBD.deletarCarro(carro);
                carrosBD.close();

                carregarCarro();
                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarCarro();
    }

    public void carregarCarro(){
        carrosBD = new CarrosBD(MainActivity.this);
        carroLista = carrosBD.getLista();
        carrosBD.close();

        if(carroLista != null){
            adapter = new ArrayAdapter<Carros>(MainActivity.this, android.R.layout.simple_list_item_1, carroLista);
            lista.setAdapter(adapter);
        }
        //finish();
    }
}
