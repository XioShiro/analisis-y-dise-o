/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebas;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class prueb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese  numeros entero y separelos por espacios:");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return;

        int[] a = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

        System.out.print("Antes:  "); for (int x : a) System.out.print(x + " ");
        System.out.println();

        for (int i = 0; i < a.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++)
                if (a[j] > a[j + 1]) { int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t; swapped = true; }
            if (!swapped) break;
        }

        System.out.print("Despues: "); for (int x : a) System.out.print(x + " ");
        System.out.println();
    }
}

