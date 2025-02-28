/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so.soriano.torres;

/**
 *
 * @author Luis Soriano
 */


public class Archivo {
    String nombre;
    int tamano;                 // Número de bloques asignados
    ListaEnlazadaBloques listaBloques;  // Lista enlazada de bloques asignados al archivo

    public Archivo(String nombre, int tamano) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.listaBloques = new ListaEnlazadaBloques(); // Inicializar la lista de bloques
    }

    // Método para mostrar los bloques asignados al archivo
    public void mostrarBloques() {
        System.out.println("Archivo: " + nombre);
        listaBloques.mostrarLista();
    }
}
