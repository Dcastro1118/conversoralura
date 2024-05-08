package com.conversor;

import  com.conversor.consultas.Consultas;
import javax.swing.*;

public class Conversor {
    static Consultas consulta = new Consultas();
    public static void main(String[] args) {
        String[] opciones = {"Consultar tipo de cambio", "Realizar conversion", "Salir"};
        JComboBox<String> comboBoxInicio = new JComboBox<>(opciones);
        int opcion;

        do {
            opcion = (JOptionPane.showConfirmDialog(null, comboBoxInicio, "Bienvenido al sistema tipos de cambio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE));
            menu(comboBoxInicio.getSelectedIndex());
        } while (opcion != 2);

    }
    static void menu(int opcion){

        switch (opcion){
            case 0:
                String[] tipoDeCambio = consulta.solicitarTipoDeCambio();
                JOptionPane.showMessageDialog(null, "El tipo de cambio solicitado de " + tipoDeCambio[1] + " a " + tipoDeCambio[2] + " es de: " + tipoDeCambio[0]);
                break;
            case 1:
                break;
            case 2:
                System.exit(0);
                break;

        }
    }
    }

