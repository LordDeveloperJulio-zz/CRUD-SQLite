package com.julio.crud.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.julio.crud.model.Carros;

import java.util.ArrayList;

public class CarrosBD extends SQLiteOpenHelper {

    private static final String DATABASE = "bdcarros";
    private static final int VERSION = 1;

    public CarrosBD(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String carro = "CREATE TABLE carros(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, modelo TEXT NOT NULL, placa TEXT NOT NULL, cor TEXT NOT NULL);";
        db.execSQL(carro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String carro = "DROP TABLE IF EXISTS carros";
        db.execSQL(carro);
    }

    public void salvarCarro(Carros carros){
        ContentValues values = new ContentValues();

        values.put("modelo", carros.getModelo());
        values.put("placa", carros.getPlaca());
        values.put("cor", carros.getCor());

        getWritableDatabase().insert("carros", null, values);
    }

    public void alterarCarro(Carros carros){
        ContentValues values = new ContentValues();

        values.put("modelo", carros.getModelo());
        values.put("placa", carros.getPlaca());
        values.put("cor", carros.getCor());

        String [] args = {carros.getId().toString()};
        getWritableDatabase().update("carros", values, "id=?", args);
    }

    public void deletarCarro(Carros carros){
        String [] args = {carros.getId().toString()};
        getWritableDatabase().delete("carros","id=?", args);
    }

    public ArrayList<Carros> getLista(){
        String [] columns = {"id", "modelo", "placa", "cor"};
        Cursor cursor = getWritableDatabase().query("carros", columns, null, null, null, null, null, null);
        ArrayList<Carros> carros = new ArrayList<Carros>();

        while (cursor.moveToNext()){
            Carros carro = new Carros();
            carro.setId(cursor.getLong(0));
            carro.setModelo(cursor.getString(1));
            carro.setPlaca(cursor.getString(2));
            carro.setCor(cursor.getString(3));

            carros.add(carro);
        }
        return carros;
    }
}
