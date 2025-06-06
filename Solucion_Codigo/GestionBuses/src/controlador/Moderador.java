package controlador;

import modelo.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Moderador {

    public static void menu(Scanner scanner) {
        int opcionModerador;

        do {
            System.out.println("===== MENU DEL MODERADOR =====");
            System.out.println("1. Registrar bus");
            System.out.println("2. Eliminar bus");
            System.out.println("3. Modificar ruta");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            if (scanner.hasNextInt()) {
                opcionModerador = scanner.nextInt();
                scanner.nextLine(); // buffer
            } else {
                System.out.println("Entrada no valida. Intente de nuevo.");
                scanner.nextLine();
                opcionModerador = -1;
                continue;
            }

            switch (opcionModerador) {
                case 1:
                    registrarBus(scanner);
                    break;
                case 2:
                    eliminarBus(scanner);
                    break;
                case 3:
                    eliminarParada(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del menu del moderador...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

            System.out.println();

        } while (opcionModerador != 4);
    }

    private static void registrarBus(Scanner scanner) {
        System.out.print("Ingrese el numero del bus: ");
        String numeroBus = scanner.nextLine();

        System.out.print("Cuantas rutas desea registrar para este bus?: ");
        int cantidadRutas = Integer.parseInt(scanner.nextLine());

        List<Ruta> rutas = new ArrayList<>();

        for (int i = 1; i <= cantidadRutas; i++) {
            System.out.println("\n--- Datos de la ruta " + i + " ---");

            System.out.print("Ingrese la direccion del bus (Desde UTPL / Hacia UTPL): ");
            String direccion = scanner.nextLine();

            System.out.print("Ingrese el nombre de la parada principal: ");
            String nombreParada = scanner.nextLine();

            System.out.print("Ingrese la ubicacion de la parada: ");
            String ubicacionParada = scanner.nextLine();

            System.out.print("Ingrese el horario para esta ruta: ");
            Horario horario = new Horario(scanner.nextLine());

            System.out.print("¿Cuantas paradas tiene esta ruta?: ");
            int numParadas = Integer.parseInt(scanner.nextLine());

            List<String> paradas = new ArrayList<>();
            for (int j = 1; j <= numParadas; j++) {
                System.out.print("Ingrese el nombre de la parada " + j + ": ");
                paradas.add(scanner.nextLine());
            }

            List<Horario> horarios = new ArrayList<>();
            horarios.add(horario);

            Ruta ruta = new Ruta(nombreParada, ubicacionParada, horarios, paradas, direccion);
            rutas.add(ruta);
        }

        Bus bus = new Bus(numeroBus, rutas);

        try (FileWriter writer = new FileWriter("bus_" + numeroBus + ".txt")) {
            writer.write(bus.toString());
            System.out.println(" Bus y todas sus rutas guardadas correctamente.");
        } catch (IOException e) {
            System.out.println(" Error al guardar el archivo: " + e.getMessage());
        }
    }

    private static void eliminarBus(Scanner scanner) {
        File carpeta = new File(".");
        File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("bus") && name.endsWith(".txt"));

        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay buses registrados para eliminar.");
            return;
        }

        System.out.println("Buses disponibles para eliminar:\n");
        for (int i = 0; i < archivos.length; i++) {
            String nombreArchivo = archivos[i].getName();
            String nombreFormateado = nombreArchivo.substring(0, nombreArchivo.length() - 4);
            nombreFormateado = nombreFormateado.replace('_', '-');
            System.out.println((i + 1) + ". " + nombreFormateado);
        }

        System.out.print("\nSeleccione el numero del bus que desea eliminar: ");
        int opcionEliminar = -1;
        if (scanner.hasNextInt()) {
            opcionEliminar = scanner.nextInt();
            scanner.nextLine(); // buffer
        } else {
            System.out.println("Entrada no valida.");
            scanner.nextLine();
            return;
        }

        if (opcionEliminar < 1 || opcionEliminar > archivos.length) {
            System.out.println("Opcion no valida.");
            return;
        }

        File archivoEliminar = archivos[opcionEliminar - 1];
        if (archivoEliminar.delete()) {
            System.out.println("Bus eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el bus.");
        }
    }

    private static void eliminarParada(Scanner scanner) {
        File carpeta = new File(".");
        File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("bus") && name.endsWith(".txt"));

        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay buses registrados.");
            return;
        }

        System.out.println("Buses disponibles:\n");
        for (int i = 0; i < archivos.length; i++) {
            System.out.println((i + 1) + ". " + archivos[i].getName());
        }

        System.out.print("Seleccione el numero del bus: ");
        int busSeleccionado = scanner.nextInt();
        scanner.nextLine();

        if (busSeleccionado < 1 || busSeleccionado > archivos.length) {
            System.out.println("Opcion no valida.");
            return;
        }

        File archivo = archivos[busSeleccionado - 1];
        String contenido;
        try (Scanner lector = new Scanner(archivo)) {
            StringBuilder sb = new StringBuilder();
            while (lector.hasNextLine()) {
                sb.append(lector.nextLine()).append("\n");
            }
            contenido = sb.toString();
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo.");
            return;
        }

        String[] bloques = contenido.split("Ruta-> ");
        if (bloques.length <= 1) {
            System.out.println("No se encontraron rutas.");
            return;
        }

        for (int i = 1; i < bloques.length; i++) {
            System.out.println(i + ". Ruta-> " + bloques[i].trim());
        }

        System.out.print("Seleccione el numero de la ruta donde desea eliminar una parada: ");
        int rutaIndex = scanner.nextInt();
        scanner.nextLine();

        if (rutaIndex < 1 || rutaIndex >= bloques.length) {
            System.out.println("Ruta invalida.");
            return;
        }

        String[] paradas = bloques[rutaIndex].split("->");
        for (int i = 0; i < paradas.length; i++) {
            System.out.println((i + 1) + ". " + paradas[i].trim());
        }

        System.out.print("Seleccione el numero de la parada a eliminar: ");
        int paradaIndex = scanner.nextInt();
        scanner.nextLine();

        if (paradaIndex < 1 || paradaIndex > paradas.length) {
            System.out.println("Parada invalida.");
            return;
        }

        String paradaEliminada = paradas[paradaIndex - 1].trim();
        String nuevaRuta = String.join(" -> ",
                Arrays.stream(paradas)
                        .filter(p -> !p.trim().equals(paradaEliminada))
                        .map(String::trim)
                        .toArray(String[]::new));

        bloques[rutaIndex] = nuevaRuta + "\n";

        StringBuilder nuevoContenido = new StringBuilder(bloques[0]);
        for (int i = 1; i < bloques.length; i++) {
            nuevoContenido.append("Ruta-> ").append(bloques[i]);
        }

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(nuevoContenido.toString());
            System.out.println("Parada eliminada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
        }
    }
}
