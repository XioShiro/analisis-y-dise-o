/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clinicaveterinaria;
import java.security.MessageDigest;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
// AuthHashChainingSimple.java
// MUY simple: Encadenamiento (LinkedList) para username -> hash(password)
// NOTA: esto es solo para aprender. No usar en producción (no tiene salt ni PBKDF2).


public class ClinicaVeterinaria {

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
    public ClinicaVeterinaria int cap) {
        capacity = Math.max(2, cap);
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) buckets[i] = new LinkedList<>();
    }

    // índice 
    private int index(String key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7fffffff) % capacity;
    }

    // Hash hex
    private String sha256Hex(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(s.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(b.length * 2);
            for (byte x : b) {
                sb.append(String.format("%02x", x & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // guarda username con hash y si existe, actualiza el hash
    public void insert(String username, String password) {
        if (username == null) return;
        int i = index(username);
        String h = sha256Hex(password);
        for (Entry e : buckets[i]) {
            if (e.username.equals(username)) { e.passHash = h; return; }
        }
        buckets[i].add(new Entry(username, h));
    }

    // busca user y compara hash
    public boolean verify(String username, String password) {
        if (username == null) return false;
        int i = index(username);
        String h = sha256Hex(password);
        for (Entry e : buckets[i]) {
            if (e.username.equals(username)) return e.passHash.equals(h);
        }
        return false;
    }

    // Buscar: devuelve el hash almacenado o null
    public String getHash(String username) {
        if (username == null) return null;
        int i = index(username);
        for (Entry e : buckets[i]) if (e.username.equals(username)) return e.passHash;
        return null;
    }

    // Eliminación: borra el usuario si existe
    public boolean remove(String username) {
        if (username == null) return false;
        int i = index(username);
        var it = buckets[i].iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            if (e.username.equals(username)) { it.remove(); return true; }
        }
        return false;
    }


    public static void main(String[] args) {
        ClinicaVeterinaria auth = new ClinicaVeterinaria(8);

        auth.insert("ana", "clave123");
        auth.insert("carlos", "miPass");

        System.out.println("ana correcto? " + auth.verify("ana", "clave123"));   
        System.out.println("ana mal? " + auth.verify("ana", "otra"));           

        System.out.println("hash carlos: " + auth.getHash("carlos")); 
        auth.remove("ana");
        System.out.println("ana existe? " + (auth.getHash("ana") != null)); 
    }
}
