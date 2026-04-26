package co.edu.udistrital.ciencias.listacircular_ejercicio.model;


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

    
    
    /**
     * Constructor.
     * Inicializa una lista vacía, estableciendo los punteros en nulo, el tamaño 
     * en cero y creando una nueva instancia para la vista por consola.
     */
    public ListaJugadores() {
        
        tail = null; 
        tamanio = 0; 
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
            tail.setNext(tail);  // Se apunta a sí mismo cerrando el círculo
        } else {
            nuevoJugador.setNext(tail.getNext()); // El nuevo apunta a la cabeza actual, que es el sugiente a tail
            tail.setNext(nuevoJugador);           // El antiguo tail apunta al nuevo
            tail = nuevoJugador;                  // El nuevo se convierte en el tail
        }
        tamanio++;
    }
    
    /**
     * Busca a un jugador por su número identificador, lo elimina de la estructura 
     * y retorna el identificador del jugador que estaba a su lado (el siguiente).
     * <p>
     * Gestiona casos especiales como la eliminación del único elemento existente 
     * o la eliminación del nodo {@code tail}.
     * </p>
     *
     * @param numero El número del jugador que se desea eliminar.
     * @return El número del jugador siguiente al eliminado. 
     * Retorna {@code -1} si la lista está vacía, si el jugador no fue encontrado, 
     * o si al eliminarlo la lista quedó completamente vacía.
     */
    public int eliminarYObtenerSiguiente(int numero) {
        
        if (tail == null) return -1; //si está vacia
        
        Jugador actual = tail.getNext();  // se empieza desde la cabeza
        Jugador anterior = tail;
        int siguienteId = -1;
        
        for (int i = 0; i < tamanio; i++) {
            if (actual.getNumero() == numero) {    
                siguienteId = actual.getNext().getNumero();
                
                if (tamanio == 1) { // si era el unico elemento en la lista
                    tail = null;
                } else {
                    if (actual == tail) tail = anterior; // Si se elimina el último, el anterior pasa a ser el tail
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
    
/**
     * Construye una cadena de texto que representa el estado 
     * de los jugadores en la lista.
     *
     * @return Un {@code String} con la representación visual de la lista.
     */
    public String mostrarJugadores() {
        
        if (tail == null) {
            return "La lista está vacía.";
        }
        
        String resultado = "";
        Jugador actual = tail.getNext(); 
        
        do {
            resultado += "[" + actual.getNumero() + "] -> ";
            actual = actual.getNext();
        } while (actual != tail.getNext());  // Condición de parada, si vuelve a la cabeza
        resultado += "(Cabeza)";
        
        return resultado;
    }
    
    /**
     * Obtiene la cabeza de la lista circular.
     *
     * @return El objeto {@code Jugador} que representa la cabeza de la lista, o 
     * {@code null} si la lista está vacía.
     */
    public Jugador getCabeza() {
        return (tail == null) ? null : tail.getNext();
    }
    
    /**
     * Obtiene la cantidad actual de nodos en la lista.
     *
     * @return Un número entero que representa el tamaño de la lista.
     */
    public int getTamanio() {
        return tamanio;
    }
    
    /**
     * Busca secuencialmente un jugador utilizando su número identificador.
     *
     * @param numero El número del jugador que se desea encontrar.
     * @return El objeto {@code Jugador} correspondiente si se encuentra; 
     * de lo contrario, retorna {@code null}.
     */
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