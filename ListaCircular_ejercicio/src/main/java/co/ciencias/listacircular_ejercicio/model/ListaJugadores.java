package co.ciencias.listacircular_ejercicio.model;

import co.ciencias.listacircular_ejercicio.view.VistaConsola;

public class ListaJugadores {
    private Jugador tail;
    private int tamanio;
    private VistaConsola vc;
    
    public ListaJugadores() {
        tail = null;
        tamanio = 0;
        vc = new VistaConsola();
    }

    public void agregarJugador(int numero) {
        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setNumero(numero);

        if (tail == null) {
            tail = nuevoJugador;
            tail.setNext(tail);
        } else {
            nuevoJugador.setNext(tail.getNext());
            tail.setNext(nuevoJugador);
            tail = nuevoJugador;
        }
        tamanio++;
    }
    
    public int eliminarYObtenerSiguiente(int numero) {
        if (tail == null) return -1;
        Jugador actual = tail.getNext();
        Jugador anterior = tail;
        int siguienteId = -1;
        for (int i = 0; i < tamanio; i++) {
            if (actual.getNumero() == numero) {    
                siguienteId = actual.getNext().getNumero();
                if (tamanio == 1) {
                    tail = null;
                } else {
                    if (actual == tail) tail = anterior;
                    anterior.setNext(actual.getNext());
                }
                tamanio--;
                return (tamanio > 0) ? siguienteId : -1;
            }
            anterior = actual;
            actual = actual.getNext();
        }
        return -1; 
    }
    public void mostrarJugadores() {
        if (tail == null) {
            vc.mostrarInformacion("La lista está vacía.");
            return;
        }

        Jugador actual = tail.getNext(); 
        do {
            vc.mostrarInformacion("[" + actual.getNumero() + "] -> ");
            actual = actual.getNext();
        } while (actual != tail.getNext()); 
        vc.mostrarInformacion("(Cabeza)");
    }
    public Jugador getCabeza() {
        return (tail == null) ? null : tail.getNext();
    }
    
    public int getTamanio() {
        return tamanio;
    }
    public Jugador buscarJugador(int numero) {
        if (tail == null) return null;
        
        Jugador temp = tail.getNext(); 
        for (int i = 0; i < tamanio; i++) {
            if (temp.getNumero() == numero) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
    
}