/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebas;
    import java.util.Arrays;
/**
 *
 * @author Usuario
 */
public class listanombre {
    public static void main(String[] args) {
        String[] nombres = {"Leonor", "Ana", "Mariana", "Jorge", "Kevin"};

        insertionSort(nombres);
        
        System.out.println("Nombres actuales:" + Arrays.toString(nombres));
        System.out.println("Nombres ordenados alfabeticamente:");
        for (String nombre : nombres) {
            System.out.print(nombre + " ");
        }
    }

    public static void insertionSort(String[] array) {
        for (int i = 1; i < array.length; i++) {
            String valorActual = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(valorActual) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = valorActual;
        }
    }
}
