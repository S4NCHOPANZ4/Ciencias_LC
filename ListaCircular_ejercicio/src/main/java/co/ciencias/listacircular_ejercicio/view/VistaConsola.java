package co.ciencias.listacircular_ejercicio.view;
import java.util.Scanner;

public class VistaConsola {
    private Scanner sc;
    public VistaConsola(){
        sc = new Scanner(System.in);
        
    }    
    public void mostrarInformacion(String msj){
        System.out.println(msj);
    }
    public String leerDato(String msj){
        System.out.println(msj);
        String dato = sc.nextLine();
        return dato;
    }
}