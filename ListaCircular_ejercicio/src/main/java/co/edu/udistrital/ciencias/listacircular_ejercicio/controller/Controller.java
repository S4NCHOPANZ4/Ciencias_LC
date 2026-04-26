package co.edu.udistrital.ciencias.listacircular_ejercicio.controller;


import co.edu.udistrital.ciencias.listacircular_ejercicio.model.Jugador;
import co.edu.udistrital.ciencias.listacircular_ejercicio.model.ListaJugadores;
import java.util.Random;

/**
 * Clase principal de control contiene la lógica del juego.
 * <p>
 * Controla el flujo de los turnos y la generación de números aleatorios (dado) 
 * y las reglas de eliminación de los jugadores hasta que quede un único ganador.
 * </p>
 *
 * @version 1.0
 */

public class Controller {

    // --- Atributos ---
    private ListaJugadores lista;
    private Jugador actual;

    /**
     * Constructor.
     * Inicializa la instancia del modelo.
     */
    public Controller() {
        lista = new ListaJugadores();
    }

    /**
     * Crea una nueva lista y la llena con la cantidad de jugadores especificada, 
     * asignándoles identificadores secuenciales desde 1 hasta n.
     *
     * @param cantidad El número total de jugadores que participarán en la partida.
     */
    
    public void inicializarJugadores(int cantidad) {
        lista = new ListaJugadores();
        for (int i = 1; i <= cantidad; i++) {
            lista.agregarJugador(i);
        }
        
        actual = lista.getCabeza(); //establece el primer turno
    }

    /**
     * Simula el lanzamiento de un dado de 6 caras.
     *
     * @return Un número entero aleatorio entre 1 y 6 .
     */
    public int tirarDado() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    /**
     * Evalúa si un número dado es par o impar. 
     *
     * @param n El número entero a evaluar.
     * @return {@code true} si el número es par, {@code false} si es impar.
     */
    public boolean parImpar(int n) {
        return n % 2 == 0;
    }

    /**
     * Si el número es par, el jugador se salva y el turno pasa al siguiente. 
     * Si es impar, el jugador actual es eliminado de la lista circular.
     *
     * @param dado El resultado numérico obtenido tras tirar el dado.
     */
    public Jugador ejecutarTurno(Jugador actrual,int dado) {
        if (parImpar(dado)) {
            
            actual = actual.getNext();
        } else {
            int proximoId = lista.eliminarYObtenerSiguiente(actual.getNumero());
            actual = lista.buscarJugador(proximoId);
        }
        return actual;
    }
    
    /**
     * Método principal que Simula la partida completa.
     * Pide la cantidad inicial de jugadores e itera los turnos consecutivamente 
     * hasta que solo quede 1 jugador en la lista.
     * Retorna al ganador.
     */
    public String jugar(int cantJugadores) {
        
        inicializarJugadores(cantJugadores);
        
        String mensaje = "";
        Jugador actual = lista.getCabeza();
        
        while (lista.getTamanio() > 1) {

            int dado = tirarDado();
            mensaje+= "Salio un: " + dado + "\n";
            
            if (!parImpar(dado)) {
                mensaje += "Jugador #" + actual.getNumero() + " Muerto\n";
            } else {
                mensaje += "Jugador #" + actual.getNumero() + " Salvado\n";
            }
            
            actual = ejecutarTurno(actual,dado);
            
        }
        
        mensaje += "GANADOR FINAL: Jugador #" + lista.getCabeza().getNumero();
        
        return mensaje;
    }
    
        /**
     * Obtiene la instancia actual.
     *
     * @return El objeto {@code ListaJugadores} con el estado actual de la partida.
     */
    public ListaJugadores getLista() {
        return lista;
    }
    
     /**
     * Retorna el jugador actual
     *
     * @return el numero del jugador actual
     */
    public int getActual() {
        return (actual != null) ? actual.getNumero() : -1;
    }
    
    public int getTamanioLista() {
        return lista.getTamanio();
    }
    
    public String obtenerEstadoLista() {
        return lista.mostrarJugadores();
    }
    
    public int obtenerGanador() {
        return (lista.getCabeza() != null) ? lista.getCabeza().getNumero() : -1;
    }
}