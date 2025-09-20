/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebas1;
  import java.io.*;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Pruebas1 {
    static String[] nombres = {"Ayato","Dai","Spicke","Shiro","Osito"};
    static int[] edades = {5,7,10,2,8};
    static class Par { String nombre; int edad; Par(String n,int e){nombre=n;edad=e;} }

    // 1) Hash (búsqueda)
    static void busquedaHashDemo(String buscado) {
        Map<String,Integer> map = new HashMap<>();
        for (int i=0;i<nombres.length;i++) map.put(nombres[i].toLowerCase(), edades[i]);
        Integer edad = map.get(buscado.toLowerCase());
        System.out.println(edad != null ? buscado + " -> " + edad + " meses" : "No encontrado");
    }

    // 2) Orden interno
    static void ordenacionInternaDemo() {
        Par[] a = new Par[nombres.length];
        for (int i=0;i<nombres.length;i++) a[i] = new Par(nombres[i], edades[i]);
        Arrays.sort(a, (p1,p2) -> p1.nombre.compareToIgnoreCase(p2.nombre));
        for (Par p : a) System.out.println(p.nombre + " (" + p.edad + ")");
    }

    // 3) Orden externo (ejemplo muy simple que asume archivo formato "Nombre,edad")
    static void ordenacionExternaDemo(String in, String out, int chunkSize) throws IOException {
        // copia exacta de la técnica externalSort: dividir en chunks, ordenar, k-way merge
        // por brevedad aquí llamamos al método externo (puedes pegar implementación completa si la necesitas)
        OrdenExterno.externalSort(in, out, chunkSize);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hash demo:");
        busquedaHashDemo("shiro");
        System.out.println("\nOrden interno demo:");
        ordenacionInternaDemo();
        // Para probar externa: crea datos.txt y descomenta línea siguiente
        // ordenacionExternaDemo("datos.txt","ordenado.txt",1000);
    }

    // Clase implementación de externalSort 
    static class OrdenExterno {
        static void externalSort(String inputFile, String outputFile, int chunkSize) throws IOException {
            List<File> chunks = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                List<String> buffer = new ArrayList<>(chunkSize);
                String line;
                while ((line = br.readLine()) != null) {
                    buffer.add(line);
                    if (buffer.size() >= chunkSize) { chunks.add(writeSortedChunk(buffer)); buffer.clear(); }
                }
                if (!buffer.isEmpty()) chunks.add(writeSortedChunk(buffer));
            }
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n->n.line, String.CASE_INSENSITIVE_ORDER));
            List<BufferedReader> readers = new ArrayList<>();
            for (File f : chunks) {
                BufferedReader r = new BufferedReader(new FileReader(f));
                readers.add(r);
                String first = r.readLine();
                if (first != null) pq.add(new Node(first, readers.size()-1));
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                while (!pq.isEmpty()) {
                    Node n = pq.poll();
                    bw.write(n.line); bw.newLine();
                    String next = readers.get(n.readerIdx).readLine();
                    if (next != null) pq.add(new Node(next, n.readerIdx));
                }
            }
            for (BufferedReader r : readers) r.close();
            for (File f : chunks) f.delete();
        }
        static File writeSortedChunk(List<String> lines) throws IOException {
            Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
            File tmp = File.createTempFile("chunk", ".tmp");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
                for (String s : lines) bw.write(s + "\n");
            }
            return tmp;
        }
        static class Node { String line; int readerIdx; Node(String l,int i){line=l;readerIdx=i;} }
    }
}
