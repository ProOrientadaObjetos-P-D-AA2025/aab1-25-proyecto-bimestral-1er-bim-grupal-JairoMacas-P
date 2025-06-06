package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Usuario {

    public static void menu(Scanner scanner) {
        System.out.println("===== MENU DEL USUARIO =====");

        File carpeta = new File(".");

        File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("bus") && name.endsWith(".txt"));

        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay buses registrados aun.");
            return;
        }

        System.out.println("Buses disponibles:\n");
        for (int i = 0; i < archivos.length; i++) {
            String nombreArchivo = archivos[i].getName();
            String nombreFormateado = nombreArchivo.substring(0, nombreArchivo.length() - 4);
            nombreFormateado = nombreFormateado.replace('_', '-');
            System.out.println((i + 1) + ". " + nombreFormateado);
        }

        System.out.print("\nSeleccione el numero del bus para ver detalles: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion < 1 || opcion > archivos.length) {
            System.out.println("Opcion no valida.");
            return;
        }

        File archivoSeleccionado = archivos[opcion - 1];
        try (Scanner lector = new Scanner(archivoSeleccionado)) {
            System.out.println("\n--- Informacion del bus ---");

            String lineaAnterior = "";
            List<String> paradasActuales = new ArrayList<>();
            int numeroRuta = 1;

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                System.out.println(linea);

                if (linea.startsWith("Ruta->")) {
                    
                    String[] partes = lineaAnterior.trim().split("\\s+");
                    String horario = (partes.length >= 5) ? partes[4] : "Desconocido";

                    
                    System.out.println("Horario de esta ruta: " + horario);

                    
                    String[] paradas = linea.substring(6).split("->");
                    paradasActuales.clear();
                    for (String p : paradas) {
                        paradasActuales.add(p.trim());
                    }

                    // Calcular duración estimada
                    int duracionEstimada = paradasActuales.size() * 5;

                    
                    System.out.println("Resumen ruta " + numeroRuta + ":");
                    System.out.println(" - Paradas totales: " + paradasActuales.size());
                    System.out.println(" - Duracion estimada: " + duracionEstimada + " minutos");
                    System.out.println();

                    numeroRuta++;
                }

                lineaAnterior = linea;
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo.");
        }
    }
}

