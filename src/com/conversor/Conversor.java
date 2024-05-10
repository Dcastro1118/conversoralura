package com.conversor;

import  com.conversor.consultas.Consultas;
import com.conversor.modelos.Historial;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {

    //En esta parte realizo una instancia estatica de Consultas y un Scanner para trabajarlo en el codigo"
    static Consultas consulta = new Consultas();
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Historial> historiales = new ArrayList<>();

    public static void main(String[] args) {
        /*
         En el Main unicamente llamo primeramente al metodo que me actualiza las monedas disponibles, y despues con un do- while, un Sout y un Scanner pregunto
         al usuario que desea hacer dentro de una lista de opciones que le muestro, luego con la opcion elegida inicio el metodo menu().
        */
        consulta.actualizarMonedas();

        int opcion = 0;
        do {
            System.out.println("Bienvenido al sistema de consultas y conversiones de monedas: \n" +
                    "1. Consultar tipo de cambio \n" +
                    "2. Convertir un monto de una moneda a otra \n" +
                    "3. Consultar los codigos de monedas \n" +
                    "4. Historial de conversiones \n" +
                    "5. Salir \n" +
                    "Si necesitas conocer los codigos de monedas que tenemos disponibles los puedes consultar en la seccion 3.\n" +
                    "Ingrese un numero segun la opcion que desee realizar:");
            try {
                opcion = Integer.parseInt(scanner.next());
                menu(opcion);
            } catch (NumberFormatException | InputMismatchException e){
                System.out.println("Error: formato no valido, ingrese un numero");
                opcion = 0;
            }

        } while (opcion != 5);
    }

    // Metodo menu que implementa un switch-case para ejecutar diferentes bloques de codigo segun elija el usuario.
    static void menu(int opcion){

        switch (opcion){
            case 1:
                System.out.println("Ingrese el codigo de moneda base (Example: USD):");
                String monedaBase = scanner.next().toUpperCase();
                System.out.println("Ingrese el codigo de moneda destino (Example: CRC):");
                String monedaCambio = scanner.next().toUpperCase();
                if (monedaBase.length() == 3 && monedaCambio.length() == 3) {
                    double tipoDeCambio = consulta.solicitarTipoDeCambio(monedaBase, monedaCambio);
                    if (tipoDeCambio != 0) {
                        System.out.println("El tipo de cambio \nDe: " + monedaBase + "\nA: " + monedaCambio + "\nEs de: " + tipoDeCambio);
                    } else {
                        System.out.println("Error inesperado, corrobore los datos y vuelva a intentar");
                    }
                    } else{
                    System.out.println("Los codigos de monedas son incorrectos, debe ser de tres caracteres");
                }
                break;
            case 2:
                System.out.println("Ingrese el codigo de moneda base (Example: USD):");
                String monedaBase2 = scanner.next().toUpperCase();
                System.out.println("Ingrese el codigo de moneda destino (Example: CRC):");
                String monedaCambio2 = scanner.next().toUpperCase();
                if (monedaBase2.length() == 3 && monedaCambio2.length() == 3) {
                System.out.println("Ingrese el monto que desea convertir: ");
                double monto = scanner.nextDouble();
                double[] tipoDeCambio2 = consulta.conversion(monedaBase2, monedaCambio2, monto);
                if (tipoDeCambio2[0] != 0) {
                    System.out.println("La conversion de: " + monedaBase2 +
                            "\nA: " + monedaCambio2 +
                            "\nPor el monto de: " + monto + monedaBase2 +
                            "\nEs de: " + tipoDeCambio2[1] + monedaCambio2 +
                            "\nCon un tipo de cambio de: " + tipoDeCambio2[0] + " " + monedaCambio2 + " por cada " + monedaBase2);
                    Historial historial = new Historial(monedaCambio2, monedaBase2, monto, tipoDeCambio2[1]);
                    historiales.add(historial);
                } else {
                    System.out.println("Error inesperado, corrobore los datos y vuelva a intentar");
                }
                } else{
                    System.out.println("Los codigos de monedas son incorrectos, deben ser de 3 caracteres");
                }

                break;
            case 3:
                System.out.println("Codigos de monedas disponibles: \n" + consulta.getMonedas());
                break;
            case 4:
                String historial = mostrarHistorial();
                System.out.println(historial);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Ingrese una opcion valida del menu(1 - 4)");
                break;

        }
    }
    static String mostrarHistorial(){
    StringBuilder stringBuilder = new StringBuilder();
    int contador = 1;
    if (!historiales.isEmpty()) {
        for (Historial historial : historiales) {
            stringBuilder.append("#" + contador + "\nConversion de " + historial.getMontoBase() + " " + historial.getMonedaBase() + " A "
                    + historial.getMonedaCambio() + "\nFecha:" + historial.getFecha() +
                    "\nMonto en moneda destino:  " + historial.getMontoCambio() + " " + historial.getMonedaCambio() + "\n");
            contador++;
        }
        return stringBuilder.toString();
    } else {
        return "Historial vacio";
    }
    }
    }

