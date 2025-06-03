
package gestiion.buses;
import java.util.Scanner;


public class GestiionBuses {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
         int opcMod;
        
        System.out.println("Ingrese lo que de sea realizar");
        
        System.out.println("1. Ingresar como moderador: ");
        System.out.println("2. Ingresar como usuario: "); 
        System.out.print("Ingrese una opcion: ");
        int opc = sc.nextInt();
        
        if (opc== 1){
            
            Moderador mod = new Moderador();
            
            
            do{
                opcMod = mod.menu();
                
                switch (opcMod) {

                case 1 -> mod.RegistroParadas();
                
            }
                
            }while (opcMod != 0);
                

        }if ( opc == 2 ){
            Usuario us = new Usuario();
           
            
            do{
                opcMod = us.menu();
                
                switch (opcMod) {

                case 1 -> us.BuscarBus();
                
            }
                
            }while (opcMod != 0);
            
        }
        

        
    }
    
}
