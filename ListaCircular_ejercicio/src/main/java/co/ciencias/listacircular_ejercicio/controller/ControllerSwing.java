package co.ciencias.listacircular_ejercicio.controller;

import co.ciencias.listacircular_ejercicio.model.Jugador;
import co.ciencias.listacircular_ejercicio.view.VistaSwing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerSwing {

    private Controller controller;
    private VistaSwing vista;
    private Jugador    actual;

    public ControllerSwing() {
        controller = new Controller();
        vista      = new VistaSwing();
    }

    public void run() {
        vista.btnIniciar.addActionListener(e -> iniciarJuego());
        vista.btnSiguiente.addActionListener(e -> siguienteTurno());
    }

    private void iniciarJuego() {
        int n;
        try {
            n = vista.getCantidad();
            if (n < 2) { JOptionPane.showMessageDialog(vista, "minimo 2 jugadores."); return; }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "numero inválido."); return;
        }

        controller.inicializarJugadores(n);
        actual = controller.getLista().getCabeza();

        vista.logArea.setText("");
        vista.log("juego iniciado con " + n + " jugadores.");
        vista.lblInfo.setText("turno: jugador #" + actual.getNumero());
        vista.btnSiguiente.setEnabled(true);
        vista.btnIniciar.setEnabled(false);
        vista.campoJugadores.setEnabled(false);
        vista.tablero.actualizar(idsActuales(), actual.getNumero());
    }

    private void siguienteTurno() {
        if (actual == null) return;

        int dado    = controller.tirarDado();
        int jugador = actual.getNumero();
        boolean par = controller.parImpar(dado);

        vista.log("Jugador #" + jugador + " saco " + dado + (par ? " -> SALVADO" : " -> ELIMINADO"));

        actual = controller.ejecutarTurno(actual, dado);

        vista.tablero.actualizar(idsActuales(), actual != null ? actual.getNumero() : -1);

        if (controller.getLista().getTamanio() == 1) {
            int ganador = controller.getLista().getCabeza().getNumero();
            vista.log("GANADOR: Jugador #" + ganador);
            vista.lblInfo.setText("¡Ganador: Jugador #" + ganador + "!");
            vista.btnSiguiente.setEnabled(false);
            vista.btnIniciar.setEnabled(true);
            vista.campoJugadores.setEnabled(true);
            JOptionPane.showMessageDialog(vista, "Ganador: Jugador #" + ganador + "!");
        } else {
            vista.lblInfo.setText("Turno: Jugador #" + actual.getNumero());
        }
    }

    private List<Integer> idsActuales() {
        List<Integer> ids = new ArrayList<>();
        Jugador temp = controller.getLista().getCabeza();
        for (int i = 0; i < controller.getLista().getTamanio(); i++) {
            ids.add(temp.getNumero());
            temp = temp.getNext();
        }
        return ids;
    }
}