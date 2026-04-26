package co.edu.udistrital.ciencias.listacircular_ejercicio.model;

/**
 * 
 * Representa la entidad Jugador, actúa como un nodo dentro de una lista enlazada circular.
 * 
 * <p>
 * Esta clase encapsula la información de un jugador
 * y mantiene un puntero al siguiente jugador en el círculo.
 * </p>
 *
 * @version 1.0
 */

public class Jugador {
    
    // --- Atributos ---
    
    private int numero;  // Identificador numérico único del jugador.
    private Jugador next; //Referencia al siguiente jugador

    /**
     * Constructor.
     * Inicializa un jugador con el número 0 y sin referencia al siguiente.
     */
    public Jugador() {
        this.next = null;
        this.numero = 0;
    }

    /**
     * Constructor Sobrecarga parametrizado.
     * Crea un jugador asignándole un número de identificación 
     * específico.
     *
     * @param numero El valor entero que identificará a este jugador.
     */
    public Jugador(int numero) {
        this.numero = numero;
        this.next = null;
    }

    // --- Getters and Setters ---

    /**
     * Obtiene el número identificador del jugador.
     *
     * @return El número entero asociado al jugador.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Actualiza el número identificador del jugador.
     *
     * @param numero El nuevo valor entero a asignar al jugador.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la referencia al siguiente jugador en la lista circular.
     *
     * @return El objeto {@code Jugador} al que apunta, o {@code null} 
     * si no está enlazado.
     */
    public Jugador getNext() {
        return next;
    }

    /**
     * Establece el enlace hacia el siguiente jugador en la estructura.
     *
     * @param next El objeto {@code Jugador} que será el siguiente en la secuencia.
     */
    public void setNext(Jugador next) {
        this.next = next;
    }


    /**
     * Devuelve una representación en formato de cadena de texto del jugador.
     *
     * @return Una cadena con el formato "Jugador #[numero]".
     */
    @Override
    public String toString() {
        return "Jugador #" + numero;
    }
}