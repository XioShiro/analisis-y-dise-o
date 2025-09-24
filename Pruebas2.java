/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebas2;
  import java.io.*;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Pruebas2 {
private Object[] keys;
private Object[] values;
private int cap;


public Pruebas2 (int capacidad) {
cap = capacidad;
keys = new Object[cap];
values = new Object[cap];
}


private int idx(Object k) {
return Math.abs(k.hashCode()) % cap;
}


public void put(Object k, Object v) {
keys[idx(k)] = k;
values[idx(k)] = v;
}


public Object get(Object k) {
int i = idx(k);
if (k.equals(keys[i])) return values[i];
return null;
}
}