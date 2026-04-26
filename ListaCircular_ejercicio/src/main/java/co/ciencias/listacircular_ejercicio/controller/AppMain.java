package co.ciencias.listacircular_ejercicio.controller;

import javax.swing.SwingUtilities;

public class AppMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControllerSwing controller = new ControllerSwing();
            controller.run();
        });
    }
}
