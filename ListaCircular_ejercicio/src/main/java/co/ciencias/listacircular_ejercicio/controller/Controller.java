package co.ciencias.listacircular_ejercicio.controller;

import co.ciencias.listacircular_ejercicio.model.Jugador;
import co.ciencias.listacircular_ejercicio.model.ListaJugadores;
import co.ciencias.listacircular_ejercicio.view.VistaConsola;
import java.util.Random;

public class Controller {

    private ListaJugadores lista;
    private VistaConsola   vc;

    public Controller() {
        lista = new ListaJugadores();
        vc    = new VistaConsola();
    }


    public void inicializarJugadores(int cantidad) {
        lista = new ListaJugadores();
        for (int i = 1; i <= cantidad; i++) {
            lista.agregarJugador(i);
        }
    }

    public int tirarDado() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public boolean parImpar(int n) {
        return n % 2 == 0;
    }

    public Jugador ejecutarTurno(Jugador actual, int dado) {
        if (parImpar(dado)) {
            return actual.getNext();
        } else {
            int proximoId = lista.eliminarYObtenerSiguiente(actual.getNumero());
            return lista.buscarJugador(proximoId);
        }
    }

    public ListaJugadores getLista() {
        return lista;
    }


    public void jugar() {
        int cantidad = Integer.parseInt(vc.leerDato("Cuantos jugadores?\n"));
        inicializarJugadores(cantidad);

        Jugador actual = lista.getCabeza();

        while (lista.getTamanio() > 1) {
            actual = tirarDados(actual);
        }

        vc.leerDato("\nGANADOR FINAL: Jugador #" + lista.getCabeza().getNumero());
    }

    public Jugador tirarDados(Jugador actual) {
        int dado = tirarDado();
        vc.mostrarInformacion("Salio un: " + dado);

        if (!parImpar(dado)) {
            vc.mostrarInformacion(actual.getNumero() + " Muerto");
        } else {
            vc.mostrarInformacion("Salvado");
        }

        actual = ejecutarTurno(actual, dado);

        vc.mostrarInformacion("Quedan:");
        lista.mostrarJugadores();

        return actual;
    }

    public void run() {
        jugar();
    }
}