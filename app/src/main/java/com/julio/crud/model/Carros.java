package com.julio.crud.model;

import java.io.Serializable;

public class Carros implements Serializable {

    private Long id;
    private String modelo;
    private String placa;
    private String cor;

    @Override
    public String toString() {
        return modelo.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
