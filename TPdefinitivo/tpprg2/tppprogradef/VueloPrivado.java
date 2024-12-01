package tppprogradef;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VueloPrivado extends Vuelo{	
	private static final String TIPO = "PRIVADO";
	private int jetsNecesarios;
	private double precioBasePorJet;
	private double precioPasaje;
	private int cantAsientos[];
	private int dniComprador;
	private int acompaniantes[];
	private int capMax;
	private int precios[];

	
	public VueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio, int dniComprado, int[]acompaniantes) {
	
    super(origen, destino, fecha);
    this.precioBasePorJet = precioBasePorJet;
    
    this.cantAsientos = new int[acompaniantes.length + 1]; // Comprador + Acompañantes
    for (int i = 0; i < cantAsientos.length; i++) {
    	
        this.cantAsientos[i] = 1; // Cada persona ocupa un asiento
    }
    this.precios = new int[]{(int) precio};
    this.acompaniantes = acompaniantes;
    
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
    
	

	public int getCantidadJets() {
		// TODO Auto-generated method stub
		return 1;
	}
	

	public String getTipoVuelo() {
		return String.format("%s (%d)", TIPO, 1);
	    	
	    }

	public void setCodigoVuelo(String codigoVuelo) {
		this.codigo = codigoVuelo;
		
	}

	@Override
	public Map<Integer, String> asientosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	    public boolean esSimilar(Vuelo vuelo) {
	        if (this.destino.equals(vuelo.destino)) {
	            return true;
	        }
	        return false;
	    }
	    
	    public double calcularRecaudacion() {
	        double recaudacion = 0.0;
	        for (int i = 0; i < precios.length; i++) {
	        	if (precios.length > i) {
	        	    double valor = precios[i];
	        	} else {
	        	    throw new RuntimeException("El índice no es válido para el arreglo de precios.");
	        	} 	
	            recaudacion += precios[i] * cantAsientos[i]; //precio por asiento * cantidad de asientos en cada sección
	        }	
	        
	        int totalPasajeros = 140; 
	        recaudacion += totalPasajeros * precioBasePorJet * jetsNecesarios;

	     //impuestos
	        recaudacion *= 1.3;

	        return recaudacion;
	    }
	    
	    public void cantAsientos()
	    {
	    	this.cantAsientos = cantAsientos;
	    }	    

		
	}
	
