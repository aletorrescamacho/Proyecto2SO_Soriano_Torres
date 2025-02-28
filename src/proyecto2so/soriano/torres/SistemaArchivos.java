/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so.soriano.torres;

/**
 *
 * @author Luis Soriano
 */

//comment commit;

// clase que maneja la asignacion de bloques a archivos

public class SistemaArchivos {
    private static final int NUMERO_BLOQUES = 35; // Número fijo de bloques
    private boolean[] bitmap; // Bitmap para gestionar bloques libres
    private Bloque[] bloques; // Array de bloques del disco
    private int bloquesLibres; // Contador de bloques disponibles

    public SistemaArchivos() {
        this.bitmap = new boolean[NUMERO_BLOQUES]; // Inicialmente, todos los bloques están libres
        this.bloques = new Bloque[NUMERO_BLOQUES];
        this.bloquesLibres = NUMERO_BLOQUES; // Todos los bloques están libres al inicio

        // Inicializar los bloques y el bitmap
        for (int i = 0; i < NUMERO_BLOQUES; i++) {
            bloques[i] = new Bloque(i);
            bitmap[i] = false; // Todos los bloques están libres al inicio
        }
    }

    // Función para asignar bloques a un archivo y actualizar bloquesLibres
    public void asignarBloquesArchivo(Archivo archivo, int n) {
        if (n > bloquesLibres) {
            System.out.println("Error: No hay suficientes bloques libres.");
            return;
        }

        int contador = 0;
        Bloque anterior = null;

        for (int i = 0; i < NUMERO_BLOQUES && contador < n; i++) {
            if (!bitmap[i]) { // Bloque libre encontrado
                bitmap[i] = true; // Marcar como ocupado
                bloques[i].ocupado = true; // Actualizar estado del bloque
                bloquesLibres--; // Reducir el contador de bloques libres

                if (anterior != null) {
                    anterior.siguiente = bloques[i]; // Enlazar el bloque anterior con el nuevo
                }

                archivo.listaBloques.agregarBloque(bloques[i]); // Agregar a la lista enlazada del archivo
                anterior = bloques[i];
                contador++;
            }
        }

        if (contador < n) {
            System.out.println("Error: No hay suficientes bloques disponibles.");
        } else {
            System.out.println("Archivo '" + archivo.nombre + "' asignado con " + n + " bloques.");
        }
    }

    //  Función para liberar los bloques de un archivo y actualizar bloquesLibres
    public void liberarBloquesArchivo(Archivo archivo) {
        Bloque actual = archivo.listaBloques.getPrimerBloque();

        while (actual != null) {
            int id = actual.id;
            bitmap[id] = false; // Marcar como libre en el bitmap
            bloques[id].ocupado = false; // Actualizar estado del bloque
            bloquesLibres++; // Aumentar el contador de bloques libres

            Bloque siguiente = actual.siguiente;
            actual.siguiente = null; // Romper la conexión
            actual = siguiente;
        }

        archivo.listaBloques = new ListaEnlazadaBloques(); // Resetear la lista del archivo
        System.out.println("Se liberaron los bloques del archivo '" + archivo.nombre + "'.");
    }

    //  Método para obtener la cantidad de bloques libres
    public int getBloquesLibres() {
        return bloquesLibres;
    }
}


