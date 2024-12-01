package tppprogradef;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Vuelo {
	protected String origen;
	protected String destino;
	protected String fecha;
	protected int precioPasaje;
	protected String horaSalida;
	protected String horaLlegada;
    protected String aeropuertoSalida;
	protected String aeropuertoLlegada;
    protected List<Asiento> asientos; // Lista de asientos
    public String codigo;
    protected Asiento asiento;
    protected HashMap<Integer, String> registroPasajeros;
    private List<Seccion> seccioness;
    protected int tripulantes;
    protected HashMap<Integer, Asiento> asientosOcupados = new HashMap<>();
	public List<Pasajero> pasajeros;
	protected String[] secciones = { "Turista", "Ejecutiva", "Primera" };
	protected List<Integer> asientosOcupadoss = new ArrayList<>();
	private Asiento[] asientoss;
    private static final int MAX_ASIENTOS = 140;
    private static final int MIN_ASIENTOS = 10;
    private int cantidadAsientos;
    protected double totalRecaudado;
    protected int[] precios;
	
    
	public Vuelo(String origen, String destino, String fecha) {
		
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.registroPasajeros = new HashMap<>();
		this.pasajeros = new ArrayList<>();
		this.codigo = codigo;
		this.asientoss = asientoss;
		this.precios = null;	
	}
	


	@Override
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
	
	public void setCodigo(String codigoVuelo) {
		this.codigo = codigoVuelo;
		
	}


	public void cancelarPasaje (String dni, String codigo, int numeroAsiento) {
		
	}

	
	public void registrarPasajero(int dni, String nombre) {
	    if (registroPasajeros.containsKey(dni)) {
	        throw new IllegalStateException("El pasajero ya está registrado en este vuelo.");
	    }
	    registroPasajeros.put(dni, nombre); 
	}


	public HashMap<Integer, Asiento> getAsientosOcupados() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean tieneAsientosDisponibles() {
		// TODO Auto-generated method stub
		return false;
	}


	public int asignarAsiento(Object seccion2) {
		// TODO Auto-generated method stub
		return 0;
	}



//	public List<Pasajero> ObtenerPasajeros() {
//        List<Pasajero> pasajeros = new ArrayList<>();
//        for (Asiento asiento : asientos) {
//            if (!asiento.isDisponible()) {
//                pasajeros.add((Pasajero) asiento.ObtenerCliente());
//            }
//        }
//        return pasajeros;
//    }
	
	public List<Pasajero> ObtenerPasajeross(){
			return this.pasajeros;
		
	}


	public void removerPasajero(Pasajero pasajero) {
		// Eliminar al pasajero del registro de pasajeros
	    registroPasajeros.remove(pasajero);
	    
	    // Buscar el asiento que ocupa el pasajero y liberarlo
	    Asiento asiento = asientosOcupados.get(pasajero.dni);
	    if (asiento != null) {
	        asiento.liberar();  // Liberar el asiento
	        asientosOcupados.remove(pasajero.dni);  // Eliminar la entrada del mapa
	    }
	}
	

	public void agregarPasajero(Pasajero pasajero) {
        this.pasajeros.add(pasajero);
    }

    // Método para eliminar un pasajero del vuelo
    public void eliminarPasajero(Pasajero pasajero) {
        this.pasajeros.remove(pasajero);
    }
    
//    public void reprogramarVuelo(Pasajero pasajero, Vuelo nuevoVuelo) {
//        // Eliminar al pasajero del vuelo original
//        this.eliminarPasajero(pasajero);
//
//        // Intentar asignar un asiento en el nuevo vuelo
//        Asiento asientoNuevo = nuevoVuelo.asignarAsiento(pasajero);
//
//        if (asientoNuevo != null) {
//            // Si se asignó un asiento, reprogramar al pasajero en el nuevo vuelo
//            pasajero.reprogramarVuelo(nuevoVuelo, asientoNuevo);
//            nuevoVuelo.agregarPasajero(pasajero); // Agregar al pasajero al nuevo vuelo
//        } else {
//            // Si no hay asientos disponibles, lanzar excepción o manejar el error
//            throw new IllegalStateException("No hay asientos disponibles en el nuevo vuelo.");
//        }
//    }
    
//    public Asiento asignarAsiento(Pasajero pasajero) {
//        for (Seccion seccion : secciones) {
//            Asiento asiento = seccion.asignarAsiento(pasajero); // Intenta asignar un asiento
//            if (asiento != null) {
//                return asiento; // Si se asignó con éxito, se devuelve el asiento
//            }
//        }
//        return null; // Si no se pudo asignar el asiento en ninguna sección
//    }
    
    public String getTipoVuelo() {
		return null;
	}    

public abstract Map<Integer, String> asientosDisponibles(); 


public void ocuparAsiento(int nroAsiento) {
	if(nroAsiento < 1 || nroAsiento > 140)
		throw new RuntimeException("El asiento debe estar entre 1 y 140");
    Asiento asiento = asientos.get(nroAsiento);
    if (asiento != null) {
        asiento.ocupar();
    }
}

//public boolean asientoEstaDisponible(int nroAsiento) {
//    Asiento asiento = asientos.get(nroAsiento); // asientos es un Map<Integer, Asiento>
//    return asiento != null && asiento.isDisponible();
//}

public String obtenerCodigo() {
	return this.codigo;
}

public String obtenerCodigo(String codigo) {
	return this.codigo;
}


//Método para verificar si este vuelo es similar a otro
public boolean esSimilar(String destino, String seccion) {
    // Por defecto, solo verifica el destino
    return this.destino.equals(destino);
}

public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Vuelo vuelo = (Vuelo) obj;
    return Objects.equals(codigo, vuelo.codigo);
}

public List<Seccion> ObtenerSecciones() {
    return this.seccioness;
}

public int obtenerCapacidad() {
    return asientoss.length; // Retorna la cantidad total de asientos en el vuelo
}

private void obtenerCapacidadd() {
	this.asientoss = asientoss;
}

//abstract calcularRecaudacion
public abstract double calcularRecaudacion();

public double getTotalRecaudado() {
    return totalRecaudado;
}


}
	
    
