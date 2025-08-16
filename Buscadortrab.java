/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.buscador;

import java.util.Scanner;

/**
 *
 * @author LAB-USR-AREQUIPA
 */
public class Buscadortrab {

    public static void main(String[] args) {
        double[] notas = {10, 15, 18, 0.9, 16};

        Scanner scanner = new Scanner (System.in);
        System.out.print("Ingresa la nota que deseas buscar: ");
        double notabuscada = scanner.nextDouble();

        boolean encontrada = false;

        for (int i = 0; i < notas.length; i++) {
            if (notas[i] == notabuscada) {
                System.out.println("Nota encontrada en la posición: " + i);
                encontrada = true;
                break; 
            }
        }

        if (!encontrada) {
            System.out.println("La nota no se encuentra en el registro.");
        }
    }
}
