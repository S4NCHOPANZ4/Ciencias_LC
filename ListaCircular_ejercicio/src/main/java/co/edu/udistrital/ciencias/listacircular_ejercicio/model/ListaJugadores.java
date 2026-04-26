package co.edu.udistrital.ciencias.listacircular_ejercicio.model;

import co.edu.udistrital.ciencias.listacircular_ejercicio.view.VistaConsola;

/**
 * Estructura de datos que gestiona una Lista Enlazada Circular de objetos {@code Jugador}.
 * <p>
 * Implementa la lógica de enlace circular utilizando únicamente un puntero al 
 * último elemento ({@code tail})
 * </p>
 *
 * @version 1.0
 */

public class ListaJugadores {
    
    // --- Atributos ---
    
    private Jugador tail; //Puntero al último nodo de la lista circular.
    private int tamanio; //Contador que mantiene el número total de jugadores
    private VistaConsola vc; //Objeto de la capa de vista encargado de la salida de datos por consola.
    
    
    /**
     * Constructor.
     * Inicializa una lista vacía, estableciendo los punteros en nulo, el tamaño 
     * en cero y creando una nueva instancia para la vista por consola.
     */
    public ListaJugadores() {
        
        tail = null; 
        tamanio = 0; 
        vc = new VistaConsola(); 
    }
    

    /**
     * Crea y añade un nuevo jugador al final de la lista circular.
     *
     * @param numero El identificador numérico que se le asignará al nuevo jugador.
     */
    
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