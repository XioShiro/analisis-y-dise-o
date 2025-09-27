/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class AuthHashChainingSimple {

    // Entrada usuario
    static class Entry {
        String username;
        String passHash; // hash simple (hex)
        Entry(String u, String h) { username = u; passHash = h; }
    }

    // Tabla con encadenamiento
    private LinkedList<Entry>[] buckets;
    private int capacity;

    @SuppressWarnings("unchecked")
    public AuthHashChainingSimple(int cap) {
        capacity = Math.max(2, cap);
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) buckets[i] = new LinkedList<>();
    }
}