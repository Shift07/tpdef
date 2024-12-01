package tppprogradef;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class VueloNacional extends VueloPublico{
	private static final String TIPO = "NACIONAL";
	private String codigo;
	private int contadorVuelosPublicos = 1;
	private int tripulantes;
	private double valorRefrigerio;
	private double precios[];
	private int cantAsientos[];
	private int cantRefrigerios;
	
	

	public VueloNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, double[] precios, int[] cantAsientos) {
        super(origen, destino, fecha);

        // Asignar los valores antes de validar
        this.precios = precios;
        this.cantAsientos = cantAsientos;

        // Validar que no sean nulos
        if (this.precios == null || this.cantAsientos == null) {
            throw new RuntimeException("Los arrays de precios y cantidad de asientos no pueden ser nulos.");
        }
        if (this.precios.length != 2 || this.cantAsientos.length != 2) {
            throw new RuntimeException("Ambos arrays deben tener exactamente dos elementos.");
        }

        this.tripulantes = tripulantes;
        this.valorRefrigerio = valorRefrigerio;
        
     
    }

	private HashMap<Integer, String> nacionales = new HashMap<>();

	
	
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
	
	public boolean nacional(String origen) {
		if(this.nacionales.containsKey(origen)) {
			return true;
		}
		return false;
	}
	
	public boolean nacional() {
        return true; 
    }
	
	public String getTipoVuelo() {
		return TIPO; 	
	    }
	public String getCodigoVuelo() {
		// TODO Auto-generated method stub
		String codigoVuelo = String.format("%03d-PUB", contadorVuelosPublicos++);
		return codigoVuelo;
	}

	public void setCodigo(String codigoVuelo) {
		this.codigo = codigoVuelo;
		
	}
	
	public void inicializarAsientos(int[] cantAsientos) {
	    this.asientos = new ArrayList<>();
	    int numeroAsiento = 1;
	    // Asientos Turista
	    for (int i = 0; i < cantAsientos[0]; i++) {
	        this.asientos.add(new Asiento(numeroAsiento++, "Turista"));
	    }
	    // Asientos Ejecutiva
	    for (int i = 0; i < cantAsientos[1]; i++) {
	        this.asientos.add(new Asiento(numeroAsiento++, "Ejecutiva"));
	    }
	}
	
	public boolean esSimilar(Vuelo vuelo) {
		if (!this.destino.equals(vuelo.destino)) {
            return false;
        }

        // Verifica si la fecha está dentro del mismo rango
        LocalDate fechaVuelo = LocalDate.parse(vuelo.fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fechaVueloOriginal = LocalDate.parse(this.fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (!fechaVuelo.isAfter(fechaVueloOriginal.minusDays(7)) || !fechaVuelo.isBefore(fechaVueloOriginal.plusDays(7))) {
            return false;
        }

        // Otras condiciones que puedas considerar para vuelos similares
        // como la clase o el número de escalas
        return true;
    }
	
	public double calcularRecaudacion() {
	    double recaudacion = 0.0;

	    // Costo de los asientos vendidos por sección
	    for (int i = 0; i < precios.length; i++) {
	        recaudacion += precios[i] * cantAsientos[i]; // Precio por asiento * cantidad de asientos en cada sección
	    }

	    // Costo de los refrigerios para todos los pasajeros
	    int totalPasajeros = 0;
	    for (int asientos : cantAsientos) {
	        totalPasajeros += asientos;
	    }
	    recaudacion += totalPasajeros * cantRefrigerios * valorRefrigerio;

	    // Aplicación de impuestos (20%)
	    recaudacion *= 1.2;

	    return recaudacion;
	}
	
	}
	
