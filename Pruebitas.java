/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebitas;

import java.io.*;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Pruebitas {

    static final int TAMANO_TROZO = 5; // cantidad de números que se cargan en memoria

    public static void main(String[] args) throws Exception {
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";

        List<String> archivosTemporales = dividirEnTrozos(archivoEntrada);
        mezclarArchivos(archivosTemporales, archivoSalida);
        System.out.println("Ordenamiento externo completado. Revisar " + archivoSalida);
    }

    static List<String> dividirEnTrozos(String archivo) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        List<String> archivos = new ArrayList<>();
        List<Integer> buffer = new ArrayList<>();
        String linea;
        int contador = 0;

        while ((linea = br.readLine()) != null) {
            buffer.add(Integer.parseInt(linea));
            if (buffer.size() == TAMANO_TROZO) {
                archivos.add(guardarTrozo(buffer, contador++));
                buffer.clear();
            }
        }
        if (!buffer.isEmpty()) {
            archivos.add(guardarTrozo(buffer, contador++));
        }
        br.close();
        return archivos;
    }

    static String guardarTrozo(List<Integer> buffer, int num) throws Exception {
        Collections.sort(buffer);
        String nombre = "trozo" + num + ".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombre));
        for (int n : buffer) {
            bw.write(n + "\n");
        }
        bw.close();
        return nombre;
    }

    static void mezclarArchivos(List<String> archivos, String archivoSalida) throws Exception {
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.valor));
        List<BufferedReader> lectores = new ArrayList<>();

        for (String archivo : archivos) {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            lectores.add(br);
            String linea = br.readLine();
            if (linea != null) pq.add(new Nodo(Integer.parseInt(linea), br));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));

        while (!pq.isEmpty()) {
            Nodo nodo = pq.poll();
            bw.write(nodo.valor + "\n");
            String linea = nodo.origen.readLine();
            if (linea != null) {
                pq.add(new Nodo(Integer.parseInt(linea), nodo.origen));
            }
        }

        bw.close();
        for (BufferedReader br : lectores) br.close();
    }

    static class Nodo {
        int valor;
        BufferedReader origen;
        Nodo(int v, BufferedReader o) { valor = v; origen = o; }
    }
}