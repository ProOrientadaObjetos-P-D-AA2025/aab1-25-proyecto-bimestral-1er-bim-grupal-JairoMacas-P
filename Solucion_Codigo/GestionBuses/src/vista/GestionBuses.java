package vista;

import controlador.Moderador;
import controlador.Usuario;

import java.util.Scanner;

public class GestionBuses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("====== SISTEMA DE GESTION DE BUSES ======");
            System.out.println("1. Moderador");
            System.out.println("2. Usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // buffer

            switch (opcion) {
                case 1:
                    Moderador.menu(scanner);
                    break;
                case 2:
                    Usuario.menu(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

            System.out.println(); // Espacio entre ciclos
        } while (opcion != 3);

        scanner.close();
    }
}

