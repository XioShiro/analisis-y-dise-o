/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebas;

/**
 *
 * @author Usuario
 */

public class Pruebas {
    public static void main(String[] args) {
        int[] a = {10, 2, 6, 9, 4};

        System.out.print("Antes:  ");
        for (int x : a) System.out.print(x + " ");
        System.out.println();

        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        } 

        System.out.println("Despues: ");
        for (int x : a) System.out.print(x + " ");
        System.out.println();
    }
}

