package gestiion.buses;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {
    Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("\n--- MENU DE USUARIO ---");
        System.out.println("1. Consultar un bus");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        return sc.nextInt();
    }

    public void BuscarBus() {
        sc.nextLine(); 

        
        File carpeta = new File(".");
        File[] archivos = carpeta.listFiles();

        System.out.println("\nEstos son los numeros de buses disponibles:");
        boolean hayBuses = false;

        if (archivos != null) {
            for (File archivo : archivos) {
                String nombre = archivo.getName();
                if (nombre.startsWith("bus ") && nombre.endsWith(".txt")) {
                    String numBus = nombre.substring(4, nombre.length() - 4); 
                    System.out.println("- " + numBus);
                    hayBuses = true;
                }
            }
        }

        if (!hayBuses) {
            System.out.println("No hay buses registrados.");
            return;
        }

        // Pedir numero de bus
        System.out.print("\nIngrese el numero de bus que desea consultar: ");
        String nBus = sc.nextLine();

        File archivo = new File("bus " + nBus + ".txt");

        if (!archivo.exists()) {
            System.out.println("No se encontro informacion para el bus numero " + nBus);
            return;
        }

        System.out.println("\nInformacion del Bus " + nBus + ":");
        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrio un error al leer el archivo.");
            e.printStackTrace();
        }
    }
}
