package tppprogradef;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VueloPublico extends Vuelo {
	public static final int costoRefrigerio = 2000;
	//private List<Asiento> asientos;
	private double valorRefrigerio;
    private double[] precios;
    private int[] cantAsientos;
    private int precioPasaje;
	private List registroPasajeros;
	protected Map<String, Integer> asientosDisponiblesPorSeccion;
	private int contadorPasajes = 0; 

	
	public VueloPublico(String origen, String destino, String fecha) {
		super(origen, destino, fecha);
		this.cantAsientos = cantAsientos;
		
   
}
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	
	    // Construir el detalle del vuelo
	    sb.append(codigo)  
	      .append(" - ")
	      .append(this.origen)
	      .append(" - ")
	      .append(this.destino)
	      .append(" - ")
	      .append(this.fecha)
	      .append(" - ")
	      .append(getTipoVuelo());
	    
	    return sb.toString();
	}


	public double calcularRecaudacion(Pasajero pasajero) {
        // Lógica para calcular la recaudación de un vuelo privado, teniendo en cuenta
        // la cantidad de acompañantes y el precio final del vuelo
        double precioFinal = this.precioPasaje * 0.20; // Ejemplo de un 20% adicional por impuestos
        return precioFinal * this.registroPasajeros.size();  // Multiplicado por la cantidad de pasajeros
    }
	
public String getTipoVuelo() {
	return null;
    	
    }


@Override
public double calcularRecaudacion() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Map<Integer, String> asientosDisponibles() {
    Map<Integer, String> disponibles = new HashMap<>();
    int asientoInicial = 1;

    for (int i = 0; i < cantAsientos.length; i++) {
        for (int j = 0; j < cantAsientos[i]; j++) {
            int numeroAsiento = asientoInicial + j;

            if (!asientosOcupadoss.contains(numeroAsiento)) {
                disponibles.put(numeroAsiento, secciones[i]);
            }
        }
        asientoInicial += cantAsientos[i];
    }

    return disponibles;
}




public boolean asientoLibre(String seccion) {
    return asientosDisponiblesPorSeccion.getOrDefault(seccion, 0) > 0;
}

public int venderPasaje(int dni, String seccion) {
    if (!asientoLibre(seccion)) {
        throw new RuntimeException("No hay asientos disponibles en esta sección.");
    }
    asientosDisponiblesPorSeccion.put(seccion, asientosDisponiblesPorSeccion.get(seccion) - 1);
    return generarCodigoPasaje(); 
}

private int generarCodigoPasaje() {
	return ++contadorPasajes;	
}




}


