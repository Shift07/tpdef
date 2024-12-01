package tppprogradef;

public class Pasajero extends Cliente{
    protected int nroAsiento;
    protected String seccion;
    protected String codigo;
    private Vuelo vueloActual;
    private Asiento asientoActual;

    
    
	public Pasajero(int dni, String nombre, String telefono, int nroAsiento) {
        super(dni, nombre, telefono);
        this.nroAsiento = nroAsiento;
    }
	
	public String toString() {
		return "Pasajero [nombre=" + nombre + ", seccion=" + seccion + ", codigo=" + codigo + ", asiento=" + asientoActual + "]";
	}



	 public void reprogramarVuelo(Vuelo nuevoVuelo, Asiento nuevoAsiento) {
	        if (nuevoVuelo != null && nuevoAsiento != null) {
	            // Cambiar vuelo y asignar asiento
	            this.vueloActual = nuevoVuelo;
	            this.asientoActual = nuevoAsiento;
	        } else {
	            throw new IllegalArgumentException("El nuevo vuelo o asiento no son válidos.");
	        }
	    }


	public boolean hayAsientosDisponibles() {
		// TODO Auto-generated method stub
		return false;
	}



	public Asiento asignarAsiento(Pasajero pasajero) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setVuelo(Vuelo vuelo) {
        // Si el pasajero ya tiene un vuelo actual, eliminarlo de la lista de ese vuelo
        if (this.vueloActual != null) {
            this.vueloActual.removerPasajero(this);
        }

        // Asignar el nuevo vuelo
        this.vueloActual = vuelo;

        // Agregar el pasajero a la lista de pasajeros del nuevo vuelo
        if (vuelo != null) {
            vuelo.agregarPasajero(this);
        }
    }

	public String obtenerSeccion() {
		// TODO Auto-generated method stub
		return seccion;
	}
	
	

    
}