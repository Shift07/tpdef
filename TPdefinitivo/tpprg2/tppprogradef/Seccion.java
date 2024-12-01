package tppprogradef;

import java.util.ArrayList;
import java.util.List;

public class Seccion {
    private List<Asiento> asientos;
    protected String nombre; // turista etc

    public Seccion(int cantidadAsientos) {
        asientos = new ArrayList<>();
        for (int i = 0; i < cantidadAsientos; i++) {
            asientos.add(new Asiento(i, null));
        }
    }
    public String toString() {
	    StringBuilder sb = new StringBuilder();

	    sb.append(nombre);
	    
	    return sb.toString();
	}
    
	public boolean hayAsientosDisponibles() {
        for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                return true; // Si hay al menos un asiento disponible, devuelve true
            }
        }
        return false; // Si no hay asientos disponibles
    }

	public Asiento asignarAsiento(Pasajero pasajero) {
		for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                asiento.asignarA(pasajero); // Asignamos el asiento al pasajero
                return asiento;
            }
        }
        return null; // Si no hay asientos disponibles
    }
	
	public List<Asiento> obtenerAsientos() {
        return this.asientos;
    }
	
	public String obtenerNombre() {
		return this.nombre;
	}

}
