package com.conversor.consultas;

import com.conversor.modelos.MonedasDisponibles;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.conversor.modelos.TipoDeCambio;
import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consultas {
    String[] monedas = {};
    HttpClient client = HttpClient.newHttpClient();
    String direccion = "https://v6.exchangerate-api.com/v6/87bd9b17f46d2a4580299e29/";



    void actualizarMonedas(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion + "codes"))
                .build();
        try {
            HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            MonedasDisponibles monedasDisponibles = gson.fromJson(json, MonedasDisponibles.class); 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String[] solicitarTipoDeCambio(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        JComboBox<String> comboBoxBase = new JComboBox<>(monedas);
        JComboBox<String> comboBoxCambio = new JComboBox<>(monedas);
        TipoDeCambio tipoDeCambio;


        int monedaBase = JOptionPane.showConfirmDialog(null, comboBoxBase, "Selecciona una moneda base",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        int monedaCambio = JOptionPane.showConfirmDialog(null, comboBoxCambio, "Selecciona una moneda cambio",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion + "pair/" + monedas[comboBoxBase.getSelectedIndex()] + "/" + monedas[comboBoxCambio.getSelectedIndex()]))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            tipoDeCambio = gson.fromJson(json, TipoDeCambio.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String[] tipoDeCambioArray = {String.valueOf(tipoDeCambio.conversionRate()), monedas[comboBoxBase.getSelectedIndex()], monedas[comboBoxCambio.getSelectedIndex()]};


        return  tipoDeCambioArray;

    }
}
