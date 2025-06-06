package modelo;

import java.util.List;

public class Ruta {
    private String nombreParada;
    private String ubicacion;
    private List<Horario> horarios;
    private List<String> paradas;
    private String direccion; // NUEVO

    public Ruta(String nombreParada, String ubicacion, List<Horario> horarios, List<String> paradas, String direccion) {
        this.nombreParada = nombreParada;
        this.ubicacion = ubicacion;
        this.horarios = horarios;
        this.paradas = paradas;
        this.direccion = direccion;
    }

    public String getNombreParada() {
        return nombreParada;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public List<String> getParadas() {
        return paradas;
    }

    public String getDireccion() {
        return direccion;
    }

    public String obtenerRutaComoTexto() {
        return "Ruta-> " + String.join(" -> ", paradas);
    }
}
