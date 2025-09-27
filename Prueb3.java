/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prueb3;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */

// Clase para guardar un usuario (mascota o dueño)
class Cuenta {
    String nombreUsuario;
    String contraseña;

    public Cuenta(String nombre, String pass) {
        this.nombreUsuario = nombre;
        this.contraseña = pass;
    }
}

// Tabla hash 
class SistemaVeterinaria {
    private List<Cuenta> cuentas = new ArrayList<>(); 
    // Insertar nueva cuenta
    public void crearCuenta(String nombre, String pass) {
        // Primero: ¿ya existe?
        for (Cuenta c : cuentas) {
            if (c.nombreUsuario.equals(nombre)) {
                System.out.println("Ya existe una cuenta con ese nombre: " + nombre);
                return;
            }
        }
        // Si no existe, la creamos
        cuentas.add(new Cuenta(nombre, pass));
        System.out.println("Cuenta creada para: " + nombre);
    }

    // Buscar contraseña (devuelve la contraseña si existe, o null si no)
    public String obtenerContraseña(String nombre) {
        for (Cuenta c : cuentas) {
            if (c.nombreUsuario.equals(nombre)) {
                return c.contraseña;
            }
        }
        return null; // No encontrada
    }

    // Eliminar cuenta
    public void eliminarCuenta(String nombre) {
        for (Cuenta c : cuentas) {
            if (c.nombreUsuario.equals(nombre)) {
                cuentas.remove(c);
                System.out.println("🗑️ Cuenta eliminada: " + nombre);
                return;
            }
        }
        System.out.println("No se encontró la cuenta: " + nombre);
    }
}

// Programa principal
public class Prueb3 {
    public static void main(String[] args) {
        SistemaVeterinaria sistema = new SistemaVeterinaria();

        // Ejemplos: mascotas que se registran para ver sus análisis
        sistema.crearCuenta("firulais", "miau123");
        sistema.crearCuenta("luna", "guau456");
        sistema.crearCuenta("rocky", "woof789");

        // Buscar
        String pass = sistema.obtenerContraseña("Doki");
        if (pass != null) {
            System.out.println("🔒 Contraseña de Doku: " + pass);
        } else {
            System.out.println("Doki no tiene cuenta.");
        }

        // Eliminar
        sistema.eliminarCuenta("Firulais");

        // Ver si ya no está
        if (sistema.obtenerContraseña("firulais") == null) {
            System.out.println("firulais ya no tiene acceso.");
        }
    }
}

