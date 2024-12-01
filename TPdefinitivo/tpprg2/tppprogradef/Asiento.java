package tppprogradef;

import java.util.List;

public class Asiento {
	    private boolean disponible;
	    private Integer numeroAsiento;
	    private String seccion;
		private Cliente pasajero;
		protected List<Asiento> asientos;
		 private String nombre; // "Turista", "Ejecutiva", "Primera"


	    public Asiento(int numeroAsiento, String seccion) {
	    	this.numeroAsiento = numeroAsiento;
	    	this.seccion = seccion;
	    	this.disponible = true; //por defecto esta disponible
	    
			
		}
	    public String toString() {
		    StringBuilder sb = new StringBuilder();
		
		    // Construir el detalle del vuelo
		    sb.append(this.numeroAsiento)  
		      .append(" - ")
		      .append(this.seccion)
		      .append(" - ")
		      .append(this.disponible);		    
		    return sb.toString();
		}

		public boolean isDisponible() {
	        return disponible;
	    }

		public void ocupar(Cliente pasajero) {
	        if (!disponible) {
	            throw new IllegalStateException("El asiento ya está ocupado");
	        }
	        this.pasajero = pasajero;
	        this.disponible = false;
	    }
		public void ocupar() {}

		public void liberar() {
	        this.pasajero = null;
	        this.disponible = true;
	    }

	    // Obtener el cliente asociado al asiento
	    public Cliente ObtenerCliente() {
	        return pasajero;
	    }

	    // Obtener la sección del asiento
	    public String ObtenerSeccion() {
	        return seccion;
	    }

		public Object getPasajero() {
			// TODO Auto-generated method stub
			return null;
		}

		public String obtenerSeccion() {
			// TODO Auto-generated method stub
			return seccion;
		}
		
		public void asignarA(Pasajero pasajero) {
	        if (disponible) {
	            this.disponible = false; // Marcar el asiento como ocupado
	            // Aquí puedes agregar lógica para asociar el pasajero al asiento si es necesario
	        }
	    }
		public Integer obtenerNumero() {
			return numeroAsiento; 
		}
		
		public Asiento buscarAsientoLibre(String seccion) {
		    for (Asiento asiento : asientos) {
		        if (asiento.isDisponible() && asiento.ObtenerSeccion().equals(seccion)) {
		            return asiento;
		        }
		    }
		    return null; // No hay asientos libres en la sección especificada.
		}
		
		   public String getSeccion() {
		        return this.seccion;
		    }

		    // Método para ocupar el asiento
		    public void ocupar(Pasajero pasajero) {
		        this.disponible = false;
		        // Aquí podrías asociar el asiento con el pasajero si es necesario
		    }
		
	}
	