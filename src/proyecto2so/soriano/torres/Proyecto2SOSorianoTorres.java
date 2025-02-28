/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2so.soriano.torres;

import ui.MainWindow;

/**
 *
 * @author Aless
 */
public class Proyecto2SOSorianoTorres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Asegurar ejecución en el hilo de eventos de Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true); // Mostrar la ventana principal
            }
        });
        
        //
    }
    
}
