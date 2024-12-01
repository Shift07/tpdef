package tppprogradef;

import java.util.ArrayList;
import java.util.List;

public class VueloInternacional extends VueloPublico {
	private String escalas[];
	private double valorRefrigerio;
	private int cantRefrigerios;
	private double precios[];
	private int cantAsientos[];
	private static final String TIPO = "INTERNACIONAL";
	
	public VueloInternacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio , int cantRefrigerios ,double precios[], int cantAsientos[], String escalas[]) {
		super(origen, destino, fecha);
		 this.escalas = escalas;
	        this.cantRefrigerios = cantRefrigerios;
	        this.precios = precios;
	        this.cantAsientos = cantAsientos;

	        // Validar escalas
	        if (this.escalas == null) {
	            throw new RuntimeException("El array de escalas no puede ser nulo.");
	        }
	        if (this.escalas.length > 0) {
	            for (String escala : escalas) {
	                if (escala == null || escala.isEmpty()) {
	                    throw new RuntimeException("Cada escala debe tener un nombre válido.");
	                }
	            }
	        }

	        // Validar precios y cantidad de asientos
	        if (this.precios.length != 3 || this.cantAsientos.length != 3) {
	            throw new RuntimeException("Los arrays de precios y cantidad de asientos deben tener exactamente tres elementos.");
	        }

	        // Asignar atributos adicionales
	        this.valorRefrigerio = valorRefrigerio;
	        
	       
	    }

	private int refrigerio;

	

	public String toString() {
	    StringBuilder sb = new StringBuilder();
	
	    // Construir el detalle del vuelo
	    sb.append(this.codigo)  
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

	public void agregarEscala(String string) {
		// TODO Auto-generated method stub
		
	}



	public void setCodigoVuelo(String codigoVuelo) {
		this.codigo = codigoVuelo;;
		
	}
	
	 public double calcularPrecioAsiento(Pasajero pasajero) {
	        // Lógica para calcular el precio del asiento según la sección
	        return 1;  
	    }

	    public double calcularRefrigerios() {
	        // Lógica para calcular el costo de los refrigerios
	        return this.pasajeros.size() * this.refrigerio * 2000; 
	    }
	    
	    public String getTipoVuelo() {
	    	return TIPO;
	        	
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
	        // Asientos Primera
	        for (int i = 0; i < cantAsientos[2]; i++) {
	            this.asientos.add(new Asiento(numeroAsiento++, "Primera"));
	        }
	    }	    
	    
	    public double getPrecioPorSeccion(int nroAsiento) {
	        // Verificar en qué sección está el asiento (por ejemplo, en las primeras 100 plazas puede ser una sección)
	        if (nroAsiento <= cantAsientos[0]) {
	            return precios[0]; // Primera sección
	        } else if (nroAsiento <= cantAsientos[0] + cantAsientos[1]) {
	            return precios[1]; // Segunda sección
	        } else {
	            return precios[2]; // Tercera sección
	        }
	    }
	    
	    @Override
	    public double calcularRecaudacion() {
	        double recaudacion = 0.0;

	        for (int i = 0; i < cantAsientos.length; i++) {
	            recaudacion += precios[i] * cantAsientos[i]; //precio por asiento * cantidad de asientos en cada sección
	        }	   
	        int totalPasajeros = 140; 
	        recaudacion += totalPasajeros * cantRefrigerios * valorRefrigerio;

	     //impuestos
	        recaudacion *= 1.2;
	        return recaudacion;
	    }
}