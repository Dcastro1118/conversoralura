package com.conversor.consultas;

import com.conversor.modelos.MonedasDisponibles;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.conversor.modelos.TipoDeCambio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Consultas {

    private ArrayList<String> monedas = new ArrayList<>();
    private HttpClient client = HttpClient.newHttpClient();
    private String direccion = "https://v6.exchangerate-api.com/v6/87bd9b17f46d2a4580299e29/";


    // Este metodo devuelve las monedas en un formato facil de leer, gracias al String builder
    public String getMonedas() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String lista : monedas){
            stringBuilder.append(lista).append("\n");
        }
        return stringBuilder.toString();
    }

    //  Este metodo de aqui se encarga de actualizar las listas de monedas a las disponibles.
    public void actualizarMonedas(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ArrayList<String> monedasArray = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion + "codes"))
                .build();
        try {
            HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            MonedasDisponibles monedasDisponibles = gson.fromJson(json, MonedasDisponibles.class);

            for (int i = 0; i < monedasDisponibles.supportedCodes().length; i++){
                String opcion = monedasDisponibles.supportedCodes()[i][0] + "-" + monedasDisponibles.supportedCodes()[i][1];
                monedasArray.add(opcion);

            }
            monedas = monedasArray;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // Este metodo solicida al Api el tipo de cambio que el cliente elija en los menus desplegables

    public double solicitarTipoDeCambio(String moneda1, String moneda2){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        TipoDeCambio tipoDeCambio;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion + "pair/" + moneda1 + "/" + moneda2))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            tipoDeCambio = gson.fromJson(json, TipoDeCambio.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        double tipoDeCambioDouble = tipoDeCambio.conversionRate();
        return  tipoDeCambioDouble;
    }


    /*
    Este metodo realiza la conversion de monedas, por medio de una request a la API, al inicio pense hacerlo a mano y hacer los calculos pero
    para optimizar el codigo y hacerlo mas eficiente descubri en la documentacion de que la API ya nos incluye un request para esto
    */

    public double[] conversion(String moneda1, String moneda2, double monto){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        TipoDeCambio tipoDeCambio;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion + "pair/" + moneda1 + "/" + moneda2 + "/" + monto))
                .build();
        TipoDeCambio tipoDeCambio2;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            tipoDeCambio2 = gson.fromJson(json, TipoDeCambio.class);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        double conversionResult = tipoDeCambio2.conversionResult();
        double conversionRate = tipoDeCambio2.conversionRate();
        return new double[]{conversionRate, conversionResult};
    }
}
