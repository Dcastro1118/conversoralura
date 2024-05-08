package com.conversor;

import  com.conversor.consultas.Consultas;
import javax.swing.*;

public class Conversor {

    //En esta parte realizo una instancia estatica de Consultas, una lista estatica llamada "opciones" y un JCombobox que contiene la lista "opciones"
    public static DefaultComboBoxModel<String> monedaInicio = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel<String> monedaDestino = new DefaultComboBoxModel<>();
    static Consultas consulta = new Consultas();
    static String[] opciones = {"Consultar tipo de cambio", "Realizar conversion", "Salir"};
    static JComboBox<String> comboBoxInicio = new JComboBox<>(opciones);

    public static void main(String[] args) {
        /*
         En el Main unicamente llamo primeramente al metodo que me actualiza las monedas disponibles, y despues con un do- while y un JoptionPane pregunto
         al usuario que desea hacer dentro de una lista de opciones que le muestro, luego con la opcion elegida inicio el metodo menu().
        */
        consulta.actualizarMonedas();
        int opcion;
        do {
            opcion = (JOptionPane.showConfirmDialog(null, comboBoxInicio, "Bienvenido al sistema tipos de cambio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE));
            menu(comboBoxInicio.getSelectedIndex());
        } while (opcion != 2);
    }

    // Metodo menu que implementa un switch-case para ejecutar diferentes bloques de codigo segun elija el usuario.
    static void menu(int opcion){

        switch (opcion){
            case 0:
                JComboBox<String> comboBoxBase = new JComboBox<>((ComboBoxModel) monedaInicio);
                JComboBox<String> comboBoxCambio = new JComboBox<>((ComboBoxModel) monedaDestino);
                int monedaBase = JOptionPane.showConfirmDialog(null, comboBoxBase, "Selecciona una moneda base",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                int monedaCambio = JOptionPane.showConfirmDialog(null, comboBoxCambio, "Selecciona una moneda cambio",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                double tipoDeCambio = consulta.solicitarTipoDeCambio(comboBoxBase.getSelectedItem().toString().substring(0, 3), comboBoxCambio.getSelectedItem().toString().substring(0, 3));
                JOptionPane.showMessageDialog(null, "El tipo de cambio \nDe: " + comboBoxBase.getSelectedItem().toString() + "\nA: " + comboBoxCambio.getSelectedItem().toString() + "\nEs de: " + tipoDeCambio);
                break;
            case 1:
                JComboBox<String> comboBoxBase2 = new JComboBox<>((ComboBoxModel) monedaInicio);
                JComboBox<String> comboBoxCambio2 = new JComboBox<>((ComboBoxModel) monedaDestino);
                int monedaBase2 = JOptionPane.showConfirmDialog(null, comboBoxBase2, "Selecciona una moneda base",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                int monedaCambio2 = JOptionPane.showConfirmDialog(null, comboBoxCambio2, "Selecciona una moneda cambio",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                int monto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto que desea cambiar de moneda: "));
                double[] tipoDeCambio2 = consulta.conversion(comboBoxBase2.getSelectedItem().toString().substring(0, 3), comboBoxCambio2.getSelectedItem().toString().substring(0, 3), monto);
                JOptionPane.showMessageDialog(null,
                        "La conversion de: " + comboBoxBase2.getSelectedItem().toString() +
                        "\nA: " + comboBoxCambio2.getSelectedItem().toString() +
                        "\nPor el monto de: " + monto + comboBoxBase2.getSelectedItem().toString().substring(0, 3) +
                        "\nEs de: " + tipoDeCambio2[1] + comboBoxCambio2.getSelectedItem().toString().substring(0, 3) +
                        "\nCon un tipo de cambio de: " + tipoDeCambio2[0]);


                break;
            case 2:
                System.exit(0);
                break;

        }
    }
    }

