package co.ciencias.listacircular_ejercicio.model;

public class Jugador {
    private int numero;
    private Jugador next;

    public Jugador() {
        this.next = null;
        this.numero = 0;
    }

    public Jugador(int numero) {
        this.numero = numero;
        this.next = null;
    }

    // --- Getters and Setters ---

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Jugador getNext() {
        return next;
    }

    public void setNext(Jugador next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Jugador #" + numero;
    }
}