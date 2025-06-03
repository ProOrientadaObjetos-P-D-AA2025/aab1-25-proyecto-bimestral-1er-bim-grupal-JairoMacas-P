
package gestiion.buses;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;





public class Moderador{
    Scanner sc = new Scanner (System.in);
    
    public int menu() {
        System.out.println("\nEn Este apartado como moderador podras Agregar una Paradas/Horarios ");
        
        System.out.println("1. Registro de paradas / Horarios / Rutas ");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        return sc.nextInt();
        
        
    
        
    }
    
    public void RegistroParadas() {
    sc.nextLine(); //buffer

    try {
        System.out.println("Ingrese el numero de bus: ");
        String nBus = sc.nextLine();

        File archivo = new File("bus " + nBus + ".txt");

        if (archivo.exists()) {
            System.out.println("El número de bus " + nBus + " ya está registrado.");
            return;
        }
        

        FileWriter escritor = new FileWriter(archivo, true);

        // Cabecera
        escritor.write(String.format("%-10s %-20s %-20s %-25s %-10s\n", "NrBus", "Desde/Hacia", "Nombre", "Ubicación", "Horario"));
        escritor.write("-------------------------------------------------------------------------------------------\n");

        System.out.println("Ingrese el nombre de la parada: ");
        String nom = sc.nextLine();

        System.out.println("Ingrese la ubicacion/calles: ");
        String ubi = sc.nextLine();

        System.out.println("Cuantos horarios va a ingresar?: ");
        int nH = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < nH; i++) {
            System.out.println("Ingrese el horario " + (i + 1) + ": ");
            String horario = sc.nextLine();

            System.out.println("Desde UTPL / Hacia UTPL?: ");
            String salida = sc.nextLine();
            
            String ruta = gestionRutas();

            String linea = String.format("\n%-10s %-20s %-20s %-20s %-10s", nBus, salida, nom, ubi, horario);
            escritor.write(linea);
            escritor.write("\n" + ruta + "\n");
            
            
        }

        
        escritor.close();
        System.out.println("Archivo creado correctamente.");

    } catch (IOException e) {
        System.out.println("Ocurrió un error.");
        e.printStackTrace();
    }
}
    public String gestionRutas(){ 
        
    ArrayList<String> paradas = new ArrayList<>();
    System.out.println("Cuantas paradas tendra la ruta?");
    int nParadas = sc.nextInt();
    sc.nextLine(); // limpiar buffer

    for (int i = 0; i < nParadas; i++) {
        System.out.println("Nombre de la parada " + (i + 1) + ": ");
        String parada = sc.nextLine();
        paradas.add(parada);
    }

    // Unir las paradas con flechas
    return "Ruta-> " + String.join(" -> ", paradas);
   
}
    
    
    
    
    
    
}
