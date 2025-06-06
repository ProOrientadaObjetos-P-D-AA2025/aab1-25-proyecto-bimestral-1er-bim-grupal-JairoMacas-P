package modelo;

import java.util.List;

public class Bus {
    private String numero;
    private List<Ruta> rutas;

    public Bus(String numero, List<Ruta> rutas) {
        this.numero = numero;
        this.rutas = rutas;
    }

    public String getNumero() {
        return numero;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-10s %-15s %-20s %-20s %-10s\n",
                "NrBus", "Desde/Hacia", "Parada", "Ubicacion", "Horario"));
        sb.append("-------------------------------------------------------------------------------------------\n");

        for (Ruta ruta : rutas) {
            for (Horario horario : ruta.getHorarios()) {
                sb.append(String.format("%-10s %-15s %-20s %-20s %-10s\n",
                        numero, ruta.getDireccion(), ruta.getNombreParada(), ruta.getUbicacion(), horario.getHora()));
                sb.append("Ruta-> " + String.join(" -> ", ruta.getParadas()) + "\n\n");
            }
        }

        return sb.toString();
    }
}
