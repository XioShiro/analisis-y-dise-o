/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clinicaveterinaria01;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */

public class ClinicaVeterinaria01 {
    
    private LinkedList<String>[] tabla;
    private int tamaño = 10; 

    @SuppressWarnings("unchecked")
    public ClinicaVeterinaria01() {
        tabla = new LinkedList[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    // Función hash: convierte el nombre en un número entre 0 y 9
    private int hash(String nombre) {
        return Math.abs(nombre.hashCode()) % tamaño;
    }

    // INSERTAR
    public void insertar(String perrito) {
        int i = hash(perrito);
        tabla[i].add(perrito);
    }

    // BUSCAR
    public boolean buscar(String perrito) {
        int i = hash(perrito);
        return tabla[i].contains(perrito);
    }

    // ELIMINAR
    public void eliminar(String perrito) {
        int i = hash(perrito);
        tabla[i].remove(perrito);
    }

    
    public static void main(String[] args) {
        ClinicaVeterinaria01 c = new ClinicaVeterinaria01();
        
        c.insertar("Ayato");
        c.insertar("Dai");
        c.insertar("Shiro");

        System.out.println("¿Esta Shiro en la sala de descanso? " + c.buscar("Shiro")); // true
        System.out.println("¿Esta Osito en la sala de descanso? " + c.buscar("Osito"));           // false

        c.eliminar("Ayato");
        System.out.println("¿Esta Ayato en la sala de descanso? " + c.buscar("Ayato"));         // false
    }
}