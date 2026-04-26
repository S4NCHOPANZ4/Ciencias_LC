package co.edu.udistrital.ciencias.listacircular_ejercicio.controller;


import co.edu.udistrital.ciencias.listacircular_ejercicio.model.Jugador;
import co.edu.udistrital.ciencias.listacircular_ejercicio.model.ListaJugadores;
import co.edu.udistrital.ciencias.listacircular_ejercicio.view.VistaConsola;
import java.util.Random;

/**
 * Clase principal de control contiene la lógica del juego.
 * <p>
 * Se encarga de gestionar la interacción entre la interfaz  
 * y la estructura de datos circular. 
 * Controla el flujo de los turnos y la generación de números aleatorios (dado) 
 * y las reglas de eliminación de los jugadores hasta que quede un único ganador.
 * </p>
 *
 * @version 1.0
 */

public class Controller {

    // --- Atributos ---
    private ListaJugadores lista;
    private VistaConsola   vc;

    /**
     * Constructor.
     * Inicializa las instancias del modelo y la vista.
     */
    public Controller() {
        lista = new ListaJugadores();
        vc    = new VistaConsola();
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
     * Ejecuta la lógica de un turno individual basándose en el resultado del dado.
     * Si el número es par, el jugador se salva y el turno pasa al siguiente. 
     * Si es impar, el jugador actual es eliminado de la lista circular.
     *
     * @param actual El {@code Jugador} del turno actual.
     * @param dado El resultado numérico obtenido tras tirar el dado.
     * @return El objeto {@code Jugador} al que le corresponde jugar el siguiente turno.
     */
    public Jugador ejecutarTurno(Jugador actual, int dado) {
        if (parImpar(dado)) {
            return actual.getNext();
        } else {
            int proximoId = lista.eliminarYObtenerSiguiente(actual.getNumero());
            return lista.buscarJugador(proximoId);
        }
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
     * Método principal que controla el bucle del juego.
     * Pide la cantidad inicial de jugadores e itera los turnos consecutivamente 
     * hasta que solo quede 1 jugador en la lista.
     * Retorna al ganador.
     */
    public void jugar() {
        int cantidad = Integer.parseInt(vc.leerDato("Cuantos jugadores?\n"));
        inicializarJugadores(cantidad);

        Jugador actual = lista.getCabeza();

        while (lista.getTamanio() > 1) {
            actual = tirarDados(actual);
        }

        vc.leerDato("\nGANADOR FINAL: Jugador #" + lista.getCabeza().getNumero());
    }

    /**
     * Envoltura  gráfica y lógica para un turno completo.
     * Genera el número del dado, informa a la vista del resultado y del estado 
     * del jugador, ejecuta el turno en el modelo y muestra 
     * cómo queda la lista tras la acción.
     *
     * @param actual El {@code Jugador} que debe realizar el lanzamiento.
     * @return El {@code Jugador} que tomará el siguiente turno.
     */
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